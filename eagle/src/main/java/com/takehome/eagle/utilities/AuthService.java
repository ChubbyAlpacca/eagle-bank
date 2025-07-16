package com.takehome.eagle.utilities;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String rawPassword, String storedHash) {
        return encoder.matches(rawPassword, storedHash);
    }

    public String getAuthenticatedUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthenticated");
        }
        // Assuming principal is the username or user id as a String
        return authentication.getName();
    }
}
