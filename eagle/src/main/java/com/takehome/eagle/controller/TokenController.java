//package com.takehome.eagle.controller;
//
//import io.jsonwebtoken.Jwts;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.openapitools.client.model.UserResponse;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.Date;
//
//@RestController
//@Slf4j
//@RequestMapping("/v1/auth")
//@RequiredArgsConstructor
//public class TokenController extends AuthApi{
//
//    @Value("${jwt.secret}")
//    private String secretKey;
//
//    @PostMapping
//    public String getToken(@RequestBody UserResponse userResponse) {
//        return generateJwtToken(userResponse.getId());
//    }
//
//
//    public String generateJwtToken(String userId) {
//        // Assuming you have a secret key for signing the JWT
//        String jwt = Jwts.builder()
//                .setSubject(userId)
//                .setIssuedAt(new Date())
//                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // 1 hour
////                .signWith(secretKey)
//                .compact();
//        return jwt;
//    }
//}
