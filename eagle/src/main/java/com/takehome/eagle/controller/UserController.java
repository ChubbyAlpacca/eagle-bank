package com.takehome.eagle.controller;

import com.takehome.eagle.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import com.takehome.eagle.api.UserApi;

@RestController
@Slf4j
@RequestMapping("/v1/users")
@RequiredArgsConstructor
public class UserController extends UserApi {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@RequestBody UserResponse userResponse) {
        return userService.createUser(userResponse);

    }

    @GetMapping("/{userId}")
    public UserResponse getUser(@PathVariable("userId") String userId) {
        log.info("Fetching user with ID: {}", userId);
        return userService.getuserById(userId);

    }
}
