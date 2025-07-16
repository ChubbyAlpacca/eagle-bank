package com.takehome.eagle.controller;

import com.takehome.eagle.model.GetJwtToken200Response;
import com.takehome.eagle.model.GetJwtTokenRequest;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.security.Key;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TokenControllerTest {

    @Mock
    private AuthService authService;

    @Mock
    private UserService userService;

    @InjectMocks
    private TokenController tokenController;

    private final String secretKey = "MySuperSecretKeyForJWTsMustBeLongEnough!"; // dummy key, needs to be set for tests

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Inject secretKey value manually, since in actual code it is @Value injected
        // Use reflection because private field without setter
        try {
            var field = TokenController.class.getDeclaredField("secretKey");
            field.setAccessible(true);
            field.set(tokenController, secretKey);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            fail("Failed to set secretKey field for test");
        }
    }

    @Test
    void testGetJwtToken_success() {
        String username = "testUser";
        String password = "testPassword";

        var userAuthDetails = new Object();
        when(userService.getUserAuthDetailsByUserName(username)).thenReturn(password);

        // Since verify returns boolean, stub it to return true to simulate successful verification
        when(authService.verify(password, password)).thenReturn(true);

        GetJwtTokenRequest request = new GetJwtTokenRequest();
        request.setUsername(username);
        request.setPassword(password);

        ResponseEntity<GetJwtToken200Response> response = tokenController.getJwtToken(request);

        // Verify interactions
        verify(authService, times(1)).verify(password, password);
        verify(userService, times(1)).getUserAuthDetailsByUserName(username);

        // Assert response status and token presence
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getToken());
    }


    @Test
    void testGenerateJwtToken_returnsValidToken() {
        String userId = "sampleUser";
        String password = "samplePass";
        // We only need to test that token is generated and contains correct subject and expiration in the future
        String token = tokenController.generateJwtToken(userId, password);

        assertNotNull(token);
        assertFalse(token.isEmpty());

        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        var claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();

        assertEquals(userId, claims.getSubject());
        assertNotNull(claims.getIssuedAt());
        assertNotNull(claims.getExpiration());
        assertTrue(claims.getExpiration().after(claims.getIssuedAt()));
    }

    @Test
    void testGetJwtToken_invalidPassword_throwsException() {
        String username = "testUser";
        String password = "wrongPassword";

        Object userAuthDetails = new Object();
        when(userService.getUserAuthDetailsByUserName(username)).thenReturn(password);

        // Simulate encryptionService throwing an exception on invalid password
        doThrow(new RuntimeException("Invalid password")).when(authService).verify(password, password);

        GetJwtTokenRequest request = new GetJwtTokenRequest();
        request.setUsername(username);
        request.setPassword(password);

        // Expect exception propagated or handled - here assuming it propagates
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            tokenController.getJwtToken(request);
        });

        assertEquals("Invalid password", thrown.getMessage());

        verify(authService, times(1)).verify(password, password);
        verify(userService, times(1)).getUserAuthDetailsByUserName(username);
    }
}
