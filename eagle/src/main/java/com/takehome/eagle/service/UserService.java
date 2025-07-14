package com.takehome.eagle.service;

import com.takehome.eagle.model.UserResponse;

public interface UserService {

    UserResponse createUser(UserResponse userResponse);

    UserResponse getuserById(String userId);

}
