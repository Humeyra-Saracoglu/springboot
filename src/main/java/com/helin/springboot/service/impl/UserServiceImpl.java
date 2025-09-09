package com.helin.springboot.service.impl;

import com.helin.springboot.dto.request.UserRequest;
import com.helin.springboot.dto.response.UserResponse;
import com.helin.springboot.entity.User;
import com.helin.springboot.repository.UserRepository;
import com.helin.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper  modelMapper;


    @Override
    @Transactional
    public UserResponse createUser(UserRequest request) {
        log.info("createUser() fonksiyonu başladı username={}", request.getUsername());
        if (userRepository.existsByUsername(request.getUsername())) {
            log.warn("createUser() conflict: username already exists username={}", request.getUsername());
            throw new IllegalArgumentException("Dikkat! Kullanıcı adı zaten kayıtlı"); }

        User createdUser = modelMapper.map(request, User.class);
        userRepository.save(createdUser);
        log.info("createUser() success id={} username={}", createdUser.getId(), createdUser.getUsername());
        return modelMapper.map(createdUser, UserResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponse readUser(Long id) {
        log.debug("readUser() fonksiyonu başladı id={}", id);
        User u = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warn("readUser() not found id={}", id);
                    return new IllegalArgumentException("Bu id ile eşleşen Kullanıcı bulunamadı: " + id);
                });
        return modelMapper.map(u, UserResponse.class);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponse> findAll() {
        log.info("findAll() fonksiyonu çağrıldı");
        return userRepository.findAll().stream()
                .map(u -> modelMapper.map(u, UserResponse.class))
                .toList();
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest request) {
        log.info("updateUser() fonksiyonu başladı id={}", id);

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
        log.info("deleteUser() started id={}", id);
        if (!userRepository.existsById(id)) {
            log.warn("deleteUser() not found id={}", id);
            throw new IllegalArgumentException("Bu id ile eşleşen Kullanıcı bulunamadı: " + id);
        }
        userRepository.deleteById(id);
        log.info("deleteUser() success id={}", id);
    }
}
