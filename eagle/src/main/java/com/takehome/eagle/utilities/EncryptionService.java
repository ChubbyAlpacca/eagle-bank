package com.takehome.eagle.utilities;

import org.springframework.beans.factory.annotation.Value;

public class EncryptionService {

    @Value("${encryption.salt}")
    private String salt;

    public String encrypt(String password) {
        // Implement encryption logic here
        // For demonstration, we'll just return the password as is
        return password; // Replace with actual encryption logic
    }

}
