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
    @Transactional(readOnly = true)
    public User readUser(Long id){
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Bu id ile eşleşen Kullanıcı bulunamadı." + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public User updateUser(Long id, User userUpdated) {
        User user = readUser(id);
        if (userUpdated.getUsername().equals(user.getUsername())  && userRepository.existsByUsername(userUpdated.getUsername())) {
            throw new IllegalArgumentException("Kullanıcı adı zaten kayıtlı");
        }
        return userRepository.save(userUpdated);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


}
