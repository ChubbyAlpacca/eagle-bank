package com.takehome.eagle.utilities;

import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private SecurityContext securityContext;

    @Mock
    private Authentication authentication;

    private AuthService authService;

    // Mock User class - adjust based on your actual User entity
    private static class MockUser {
        private final String userId;
        private final String username;

        public MockUser(String userId, String username) {
            this.userId = userId;
            this.username = username;
        }

        public String getUserId() {
            return userId;
        }

        public String getUsername() {
            return username;
        }
    }

    @BeforeEach
    void setUp() {
        authService = new AuthService(userRepository);
    }

    @Test
    void encrypt_ShouldReturnEncodedPassword() {
        // Arrange
        String rawPassword = "password123";

        // Act
        String encrypted = authService.encrypt(rawPassword);

        // Assert
        assertNotNull(encrypted);
        assertNotEquals(rawPassword, encrypted);
        assertTrue(encrypted.startsWith("$2a$")); // BCrypt hash format
        assertTrue(encrypted.length() > 50); // BCrypt hashes are typically 60 chars
    }

    @Test
    void encrypt_ShouldReturnDifferentHashesForSamePassword() {
        // Arrange
        String rawPassword = "password123";

        // Act
        String encrypted1 = authService.encrypt(rawPassword);
        String encrypted2 = authService.encrypt(rawPassword);

        // Assert
        assertNotEquals(encrypted1, encrypted2); // BCrypt uses random salt
    }

    @Test
    void verify_ShouldReturnTrueForCorrectPassword() {
        // Arrange
        String rawPassword = "password123";
        String storedHash = authService.encrypt(rawPassword);

        // Act
        boolean result = authService.verify(rawPassword, storedHash);

        // Assert
        assertTrue(result);
    }

    @Test
    void verify_ShouldReturnFalseForIncorrectPassword() {
        // Arrange
        String rawPassword = "password123";
        String wrongPassword = "wrongpassword";
        String storedHash = authService.encrypt(rawPassword);

        // Act
        boolean result = authService.verify(wrongPassword, storedHash);

        // Assert
        assertFalse(result);
    }

    @Test
    void getAuthenticatedUsernameFromContext_ShouldReturnUsername_WhenAuthenticated() {
        // Arrange
        String expectedUsername = "testuser";
        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn(expectedUsername);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder =
                     mockStatic(SecurityContextHolder.class)) {

            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);

            // Act
            String username = authService.getAuthenticatedUsernameFromContext();

            // Assert
            assertEquals(expectedUsername, username);
        }
    }

    @Test
    void getAuthenticatedUsernameFromContext_ShouldThrowException_WhenAuthenticationIsNull() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(null);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder =
                     mockStatic(SecurityContextHolder.class)) {

            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> authService.getAuthenticatedUsernameFromContext());

            assertEquals("Unauthenticated", exception.getMessage());
        }
    }

    @Test
    void getAuthenticatedUsernameFromContext_ShouldThrowException_WhenNotAuthenticated() {
        // Arrange
        when(authentication.isAuthenticated()).thenReturn(false);
        when(securityContext.getAuthentication()).thenReturn(authentication);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder =
                     mockStatic(SecurityContextHolder.class)) {

            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> authService.getAuthenticatedUsernameFromContext());

            assertEquals("Unauthenticated", exception.getMessage());
        }
    }

    @Test
    void getAuthenticatedUserId_ShouldReturnUserId_WhenUserExists() {
        // Arrange
        String username = "testuser";
        String expectedUserId = "user123";
        User mockUser = new User();
        mockUser.setUserId(expectedUserId);
        mockUser.setName(username);

        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn(username);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(userRepository.getUserByName(username)).thenReturn(Optional.of(mockUser));

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder =
                     mockStatic(SecurityContextHolder.class)) {

            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);

            // Act
            String userId = authService.getAuthenticatedUserId();

            // Assert
            assertEquals(expectedUserId, userId);
            verify(userRepository).getUserByName(username);
        }
    }

    @Test
    void getAuthenticatedUserId_ShouldThrowEagleBankException_WhenUserNotFound() {
        // Arrange
        String username = "testuser";

        when(authentication.isAuthenticated()).thenReturn(true);
        when(authentication.getName()).thenReturn(username);
        when(securityContext.getAuthentication()).thenReturn(authentication);
        when(userRepository.getUserByName(username)).thenReturn(Optional.empty());

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder =
                     mockStatic(SecurityContextHolder.class)) {

            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);

            // Act & Assert
            EagleBankException exception = assertThrows(EagleBankException.class,
                    () -> authService.getAuthenticatedUserId());

            assertEquals("User not found", exception.getMessage());
            assertEquals(HttpStatus.NOT_FOUND, exception.getHttpstatusCode());
            verify(userRepository).getUserByName(username);
        }
    }

    @Test
    void getAuthenticatedUserId_ShouldThrowException_WhenNotAuthenticated() {
        // Arrange
        when(securityContext.getAuthentication()).thenReturn(null);

        try (MockedStatic<SecurityContextHolder> mockedSecurityContextHolder =
                     mockStatic(SecurityContextHolder.class)) {

            mockedSecurityContextHolder.when(SecurityContextHolder::getContext)
                    .thenReturn(securityContext);

            // Act & Assert
            RuntimeException exception = assertThrows(RuntimeException.class,
                    () -> authService.getAuthenticatedUserId());

            assertEquals("Unauthenticated", exception.getMessage());
            verifyNoInteractions(userRepository);
        }
    }
}