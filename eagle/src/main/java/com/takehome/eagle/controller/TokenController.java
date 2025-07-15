package com.takehome.eagle.controller;

import com.takehome.eagle.api.AuthApi;
import com.takehome.eagle.model.GetJwtToken200Response;
import com.takehome.eagle.model.GetJwtTokenRequest;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.EncryptionService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@Slf4j
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class TokenController implements AuthApi {

    @Value("${jwt.secret}")
    private String secretKey;

    private final EncryptionService encryptionService;
    private final UserService userService;

    @Override
    public ResponseEntity<GetJwtToken200Response> getJwtToken(GetJwtTokenRequest getJwtTokenRequest) {
        encryptionService.verify(getJwtTokenRequest.getPassword(), userService.getUserAuthDetailsByUserName(getJwtTokenRequest.getUsername()));
        log.info("Generating JWT");
        var token = generateJwtToken(getJwtTokenRequest.getUsername(), getJwtTokenRequest.getPassword());
        return new ResponseEntity<>(new GetJwtToken200Response().token(token), HttpStatus.CREATED);
    }


    public String generateJwtToken(String userId, String password) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
