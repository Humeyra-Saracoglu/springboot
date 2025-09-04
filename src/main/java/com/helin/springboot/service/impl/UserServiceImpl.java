package com.helin.springboot.service.impl;

import com.helin.springboot.entity.User;
import com.helin.springboot.repository.UserRepository;
import com.helin.springboot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public User createUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new IllegalArgumentException("Dikkat! Kullanıcı adı zaten kayıtlı");

        }
        return userRepository.save(user);
    }

    @Override
    public User readUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bu id ile eşleşen Kullanıcı bulunamadı." + id));
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(Long id, User updated) {
        User user = readUser(id);
        if (updated.getUsername().equals(user.getUsername())  && userRepository.existsByUsername(updated.getUsername())) {
            throw new IllegalArgumentException("Kullanıcı adı zaten kayıtlı");
        }
        return userRepository.save(updated);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
