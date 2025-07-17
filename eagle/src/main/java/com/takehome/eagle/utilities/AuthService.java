package com.takehome.eagle.utilities;

import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

    private final UserRepository userRepository;


    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String rawPassword, String storedHash) {
        return encoder.matches(rawPassword, storedHash);
    }

    public String getAuthenticatedUsernameFromContext() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Unauthenticated");
        }
        // Assuming principal is the username or user id as a String
        return authentication.getName();
    }

    public String getAuthenticatedUserId() {
        String username = getAuthenticatedUsernameFromContext();
        var user = userRepository.getUserByName(username)
                .orElseThrow(() -> new EagleBankException("User not found", HttpStatus.NOT_FOUND));
        return user.getUserId();
    }

}
