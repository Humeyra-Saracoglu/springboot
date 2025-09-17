package com.helin.springboot.service;

import com.helin.springboot.dto.request.UserRequest;
import com.helin.springboot.dto.response.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);
    UserResponse readUser(Long id);
    List<UserResponse> findAll();
    UserResponse updateUser(Long id, UserRequest request);
    void deleteUser(Long id);
}
