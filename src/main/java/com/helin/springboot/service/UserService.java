package com.helin.springboot.service;

import com.helin.springboot.entity.User;

import java.util.List;

public interface UserService {
    User createUser(User user);
    User readUser(Long id);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    List<User> findAll();
}
