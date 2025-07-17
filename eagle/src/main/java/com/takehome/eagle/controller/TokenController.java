package com.takehome.eagle.controller;

import com.takehome.eagle.api.AuthApi;
import com.takehome.eagle.model.BadRequestErrorResponseDetailsInner;
import com.takehome.eagle.model.GetJwtToken200Response;
import com.takehome.eagle.model.GetJwtTokenRequest;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
public class TokenController implements AuthApi {

    @Value("${jwt.secret}")
    private String secretKey;

    private final AuthService authService;
    private final UserService userService;

    @Override
    public ResponseEntity<GetJwtToken200Response> getJwtToken(GetJwtTokenRequest getJwtTokenRequest) {
        boolean verified = authService.verify(getJwtTokenRequest.getPassword(), userService.getUserAuthDetailsByUserName(getJwtTokenRequest.getUsername()));
        if (!verified) {
            log.warn("Invalid credentials for user: {}", getJwtTokenRequest.getUsername());
            //intentionally vague to avoid leaking user existence
            BadRequestErrorResponseDetailsInner detailsInner = new BadRequestErrorResponseDetailsInner();
            detailsInner.setField("request");
            detailsInner.setMessage("unable to authenticate.");

//            throw new BadRequestErrorResponse().details(List.of(detailsInner));
        }
        log.info("Generating JWT");
        var token = generateJwtToken(getJwtTokenRequest.getUsername(), getJwtTokenRequest.getPassword());
        return new ResponseEntity<>(new GetJwtToken200Response().token(token), HttpStatus.CREATED);
    }


    public String generateJwtToken(String userId, String password) {
        Key key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        log.info("SECRET KEY create" +  secretKey);
        return Jwts.builder()
                .setSubject(userId)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
