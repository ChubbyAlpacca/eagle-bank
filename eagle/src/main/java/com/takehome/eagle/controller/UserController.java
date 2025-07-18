package com.takehome.eagle.controller;

import com.takehome.eagle.exceptions.EagleBankException;
import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.UpdateUserRequest;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.AuthService;
import com.takehome.eagle.utilities.UserValidator;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.takehome.eagle.api.UserApi;
import com.takehome.eagle.model.UserResponse;
@RestController
@Slf4j
@RequiredArgsConstructor
public class UserController implements UserApi {

    private final UserService userService;
    private final UserValidator userValidator;
    private final AuthService authService;

    @Override
    public ResponseEntity<UserResponse> createUser(
            @Parameter(name = "CreateUserRequest", description = "Create a new user", required = true) @Valid @RequestBody CreateUserRequest createUserRequest
    ) {
        userValidator.validateCreateUserRequest(createUserRequest);
        return ResponseEntity.status(201).body(userService.createUser(createUserRequest));
    }

    @Override
    public ResponseEntity<UserResponse> fetchUserByID(
            @Pattern(regexp = "^usr-[A-Za-z0-9]+$") @Parameter(name = "userId", description = "ID of the user", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId
    ) {
        isAuthenticatedUser(userId);
        log.info("Fetching user with ID: {}", userId);
        UserResponse userResponse = userService.getuserById(userId);
        return ResponseEntity.ok(userResponse);
    }

    @Override
    public ResponseEntity<UserResponse> updateUserByID(
            @Pattern(regexp = "^usr-[A-Za-z0-9]+$") @Parameter(name = "userId", description = "ID of the user", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId,
            @Parameter(name = "UpdateUserRequest", description = "Update user details", required = true) @Valid @RequestBody UpdateUserRequest updateUserRequest
    ) {
        isAuthenticatedUser(userId);
        return ResponseEntity.ok(userService.updateUser(userId, updateUserRequest));
    }

    @Override
    public ResponseEntity<Void> deleteUserByID(
            @Pattern(regexp = "^usr-[A-Za-z0-9]+$") @Parameter(name = "userId", description = "ID of the user", required = true, in = ParameterIn.PATH) @PathVariable("userId") String userId
    ) {
        isAuthenticatedUser(userId);
         userService.deleteUserById(userId);
        return ResponseEntity.noContent().build();
    }

    private void isAuthenticatedUser(String userId) {
        String authenticatedUserId = authService.getAuthenticatedUserId();
        if (!userId.equals(authenticatedUserId)) {
            throw new EagleBankException("Access denied.", HttpStatus.FORBIDDEN);
        }
    }
}
