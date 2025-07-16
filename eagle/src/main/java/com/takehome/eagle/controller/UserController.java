package com.takehome.eagle.controller;

import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.service.UserService;
import com.takehome.eagle.utilities.UserValidator;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
        log.info("Fetching user with ID: {}", userId);
        UserResponse userResponse = userService.getuserById(userId);
        return ResponseEntity.ok(userResponse);
    }
}
