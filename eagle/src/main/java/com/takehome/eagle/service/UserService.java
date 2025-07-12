package com.takehome.eagle.service;

import org.openapitools.client.model.UserResponse;

public interface UserService {

    UserResponse createUser(UserResponse userResponse);

    UserResponse getuserById(String userId);

}
