package com.takehome.eagle.service.impl;
import com.takehome.eagle.entity.Address;
import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import com.takehome.eagle.model.UpdateUserRequest;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.EncryptionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.takehome.eagle.model.UserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final EncryptionService encryptionService;
    @Override
    public UserResponse createUser(CreateUserRequest payload) {
        log.info("Creating user with details: {}", payload);
        var userId = UUID.randomUUID();
        var user = User.builder()
                .userId(userId)
                .name(payload.getName())
                .password(encryptionService.encrypt(payload.getPassword()))
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
        var user =  userRepository.getUserByUserId(UUID.fromString(userId));
        log.info("Fetched user with ID: {}", userId);
        if (user.isEmpty()) {
            throw new EagleBankException("User not found", HttpStatusCode.valueOf(404));
        } else {
            return mapToUserResponse(user.get());
        }
    }

    @Override
    public String getUserAuthDetailsByUserName(String userName) {
        return userRepository.getUserByName(userName)
                .map(User::getPassword)
                .orElseThrow(() -> new EagleBankException("User not found", HttpStatusCode.valueOf(404)));
    }

    @Override
    public UserResponse updateUser(String userId, UpdateUserRequest updateUserRequest) {
        Optional<User> optionalUser = userRepository.getUserByUserId(UUID.fromString(userId));
        if (optionalUser.isEmpty()) {
            throw new EagleBankException("User not found", HttpStatusCode.valueOf(404));
        }
//        optionalUser.get().
        return null;
    }

    @Override
    public void deleteUserById(String userId) {
        log.info("Retrieving user {}", userId);
        Optional<User> optionalUser = userRepository.getUserByUserId(UUID.fromString(userId));
        //TODO should do an acct check here
        optionalUser.ifPresent(userRepository::delete);
        log.info("User {} successfully deleted.", userId);
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
