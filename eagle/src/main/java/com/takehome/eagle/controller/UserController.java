package com.takehome.eagle.controller;

import com.takehome.eagle.service.UserService;
import lombok.RequiredArgsConstructor;
import org.openapitools.client.api.UserApi;
import org.openapitools.client.model.UserResponse;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class UserController extends UserApi {

//    private final UserService userService;

    public UserResponse createUser(UserResponse userResponse) {
        // Implementation for creating a user
//        System.out.println(userService.createUser(userResponse));
        return userResponse; // Placeholder return, replace with actual logic
    }

}
