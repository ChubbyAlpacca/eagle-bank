package com.takehome.eagle.service.impl;

import com.takehome.eagle.entity.User;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openapitools.client.model.UserResponse;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserResponse userResponse) {
//        log.info("Creating user with details: {}", userResponse);
        var user = new User();
//        user.setUserId(UUID.randomUUID());
//        user.setName(userResponse.getName());
//        user.setEmail(userResponse.getEmail());
//        user.setPhoneNumber(userResponse.getPhoneNumber());
        userRepository.save(user);
        // Implementation for creating a user
        // This is a placeholder implementation, replace with actual logic
        return userResponse; // Return the user response as is for now
    }
}
