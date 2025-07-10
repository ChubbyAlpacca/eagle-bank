package com.takehome.eagle.service.impl;

import com.takehome.eagle.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.UserResponse;

@Slf4j
public class UserServiceImplementation implements UserService {


    @Override
    public UserResponse createUser(UserResponse userResponse) {
//        log.info("Creating user with details: {}", userResponse);
        // Implementation for creating a user
        // This is a placeholder implementation, replace with actual logic
        return userResponse; // Return the user response as is for now
    }
}
