package com.takehome.eagle.service.impl;
import com.takehome.eagle.entity.Address;
import com.takehome.eagle.entity.User;
import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.CreateUserRequestAddress;
import com.takehome.eagle.model.UpdateUserRequest;
import com.takehome.eagle.repository.UserRepository;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.takehome.eagle.model.UserResponse;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.OffsetDateTime;
import java.time.OffsetDateTime;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {

    private final UserRepository userRepository;
    private final AuthService authService;

    private static final String PREFIX = "usr-";
    private static final String ALPHANUMERIC = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();

    @Override
    public UserResponse createUser(CreateUserRequest payload) {
        log.info("Creating user with details: {}", payload);
        var userId = UUID.randomUUID();
        var user = User.builder()
                .userId(generateUserId())
                .name(payload.getName())
                .password(authService.encrypt(payload.getPassword()))
                .email(payload.getEmail())
                .phoneNumber(payload.getPhoneNumber())
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
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
        var user =  userRepository.getUserByUserId(userId);
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
    public UserResponse updateUser(String userId, UpdateUserRequest request) {
        Optional<User> optionalUser = userRepository.getUserByUserId(userId);
        if (optionalUser.isEmpty()) {
            throw new EagleBankException("User not found", HttpStatusCode.valueOf(404));
        }
        User userEntity = optionalUser.get();
        User updatedUser = new User();
        updatedUser.setUserId(userEntity.getUserId());
        updatedUser.setName(request.getName() != null ? request.getName() : userEntity.getName());
        updatedUser.setEmail(request.getEmail() != null ? request.getEmail() : userEntity.getEmail());
        updatedUser.setPhoneNumber(request.getPhoneNumber() != null ? request.getPhoneNumber() : userEntity.getPhoneNumber());
        updatedUser.setAddress(handleAddressUpdate(request.getAddress(), userEntity.getAddress()));
        updatedUser.setCreatedAt(userEntity.getCreatedAt()); // Keep the original creation date
        updatedUser.setUpdatedAt(OffsetDateTime.now());
        User persistedUser = userRepository.save(updatedUser);
        return mapToUserResponse(persistedUser);
    }

    @Override
    public void deleteUserById(String userId) {
        log.info("Retrieving user {}", userId);
        Optional<User> optionalUser = userRepository.getUserByUserId(userId);
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
                .phoneNumber(user.getPhoneNumber())
                .createdTimestamp(user.getCreatedAt())
                .updatedTimestamp(user.getUpdatedAt());
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

    private Address handleAddressUpdate(@Valid CreateUserRequestAddress address, Address persistedAddress) {
        if (address == null) {
            return persistedAddress;
        }
        Address updatedAddress = new Address();
        updatedAddress.setLine1(address.getLine1() != null ? address.getLine1() : persistedAddress.getLine1());
        updatedAddress.setLine2(address.getLine2() != null ? address.getLine2() : persistedAddress.getLine2());
        updatedAddress.setLine3(address.getLine3() != null ? address.getLine3() : persistedAddress.getLine3());
        updatedAddress.setTown(address.getTown() != null ? address.getTown() : persistedAddress.getTown());
        updatedAddress.setCounty(address.getCounty() != null ? address.getCounty() : persistedAddress.getCounty());
        updatedAddress.setPostcode(address.getPostcode() != null ? address.getPostcode() : persistedAddress.getPostcode());
        updatedAddress.setUser(persistedAddress.getUser());
        return updatedAddress;
    }

    public static String generateUserId() {
        int length = 10;
        StringBuilder suffix = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int index = RANDOM.nextInt(ALPHANUMERIC.length());
            suffix.append(ALPHANUMERIC.charAt(index));
        }

        return PREFIX + suffix.toString();
    }
}
