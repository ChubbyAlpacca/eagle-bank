package com.takehome.eagle.service;

import com.takehome.eagle.model.CreateUserRequest;
import com.takehome.eagle.model.UserResponse;

public interface UserService {

    UserResponse createUser(CreateUserRequest request);

    UserResponse getuserById(String userId);

    String getUserAuthDetailsByUserName(String userName);

}
