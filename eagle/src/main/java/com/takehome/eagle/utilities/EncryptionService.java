package com.takehome.eagle.utilities;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EncryptionService {

    @Value("${encryption.salt}")
    private String salt;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();


    public String encrypt(String password) {
        return encoder.encode(password);
    }

    public boolean verify(String rawPassword, String storedHash) {
        return encoder.matches(rawPassword, storedHash);
    }
}
