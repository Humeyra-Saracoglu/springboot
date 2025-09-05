package com.helin.springboot.service.impl;

import com.helin.springboot.dto.request.UserRequest;
import com.helin.springboot.dto.response.UserResponse;
import com.helin.springboot.entity.User;
import com.helin.springboot.repository.UserRepository;
import com.helin.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper  modelMapper;


    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Dikkat! Kullanıcı adı zaten kayıtlı"); }

        User createdUser = modelMapper.map(request, User.class);
        userRepository.save(createdUser);
        UserResponse userResponse = modelMapper.map(createdUser, UserResponse.class);
        return userResponse;
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse readUser(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bu id ile eşleşen Kullanıcı bulunamadı: " + id));
        return modelMapper.map(u, UserResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserResponse.class))
                .toList();
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        UserResponse user = readUser(id);
        if (request.getUsername().equals(user.getUsername())  && userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("Kullanıcı adı zaten kayıtlı");}

        User updatedUser = modelMapper.map(request, User.class);
        userRepository.save(updatedUser);
        UserResponse userResponse = modelMapper.map(updatedUser, UserResponse.class);
        return userResponse;
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
