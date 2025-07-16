package com.takehome.eagle.utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String rawPassword, String storedHash) {
        return encoder.matches(rawPassword, storedHash);
    }
}
