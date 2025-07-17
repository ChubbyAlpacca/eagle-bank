package com.takehome.eagle.utilities.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

//@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    private String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String uri = request.getMethod() + " " + request.getRequestURI();
        log.info("JwtFilter processing: {}", uri);
        log.info("SECRET KEY filter" +  secretKey);
        String authHeader = request.getHeader("Authorization");
        log.info("Authorization header: {}", authHeader != null ? "Bearer " + authHeader.substring(7, Math.min(authHeader.length(), 20)) + "..." : "null");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            log.info("No valid auth header, continuing without authentication");
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);
        log.info("Token length: {}, Secret key length: {}", token.length(), secretKey.length());

        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            String userId = claims.getSubject();
            log.info("JWT validation successful for user: {}", userId);
            log.info("Token expires at: {}", claims.getExpiration());

//            UsernamePasswordAuthenticationToken authentication =
//                    new UsernamePasswordAuthenticationToken(userId, null, List.of());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(userId, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (JwtException e) {
            log.error("JWT validation failed: {}", e.getMessage());
            log.error("Exception type: {}", e.getClass().getSimpleName());
            SecurityContextHolder.clearContext();
            // Continue the chain to let Spring Security handle the 403
        }

        log.info("Continuing filter chain for: {}", uri);
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        System.out.println("JwtFilter.shouldNotFilter URI: " + request.getRequestURI());
        String path = request.getRequestURI();
        return path.startsWith("/v1/auth")
                || path.startsWith("/actuator")
                || path.equals("/v1/users");
    }
}

