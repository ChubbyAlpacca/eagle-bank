package com.takehome.eagle.service.impl;
import com.takehome.eagle.entity.Address;
import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.takehome.eagle.model.UserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(CreateUserRequest payload) {
        log.info("Creating user with details: {}", payload);
        var userId = UUID.randomUUID();
        var user = User.builder()
                .userId(userId)
                .name(payload.getName())
                .email(payload.getEmail())
                .phoneNumber(payload.getPhoneNumber())
                .createdAt(LocalDateTime.now())
                .build();
        var address = Address.builder()
                .line1(payload.getAddress().getLine1())
                .line2(payload.getAddress().getLine2())
                .line3(payload.getAddress().getLine3())
                .town(payload.getAddress().getTown())
                .county(payload.getAddress().getCounty())
                .postcode(payload.getAddress().getPostcode())
                .user(user)
                .build();
        user.setAddress(address);
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
                .address(mapToAddressResponse(user.getAddress())) // Assuming address is not part of UserResponse
                .phoneNumber(user.getPhoneNumber());
    }

    private CreateUserRequestAddress mapToAddressResponse(Address address) {
        return new CreateUserRequestAddress()
                .line1(address.getLine1())
                .line2(address.getLine2())
                .line3(address.getLine3())
                .town(address.getTown())
                .county(address.getCounty())
                .postcode(address.getPostcode());
    }
}
