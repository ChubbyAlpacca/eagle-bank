package com.takehome.eagle.service.impl;
import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.takehome.eagle.model.UserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserResponse userResponse) {
        log.info("Creating user with details: {}", userResponse);
        var user = User.builder()
                .userId(UUID.randomUUID())
                .name(userResponse.getName())
                .email(userResponse.getEmail())
                .phoneNumber(userResponse.getPhoneNumber())
                .build();
        try {
             User persistedUser = userRepository.save(user);
            return mapToUserResponse(persistedUser);
        } catch (Exception e) {
            throw new EagleBankException("Failed to create user", HttpStatusCode.valueOf(500));
        }
    }

    @Override
    public UserResponse getuserById(String userId) {
        var user =  userRepository.getUserByUserId(UUID.fromString(userId)).get();
        log.info("Fetched user with ID: {}", userId);
        return mapToUserResponse(user);
    }

    private UserResponse mapToUserResponse(User user) {
        return new UserResponse()
                .id(user.getUserId().toString())
                .name(user.getName())
                .email(user.getEmail())
                .address(null) // Assuming address is not part of UserResponse
                .phoneNumber(user.getPhoneNumber());
    }
}
