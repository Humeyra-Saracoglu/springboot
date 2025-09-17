package com.helin.springboot.service.impl;

import com.helin.springboot.dto.auth.RegisterRequest;
import com.helin.springboot.dto.auth.RegisterResponse;
import com.helin.springboot.entity.User;
import com.helin.springboot.jwt.JwtService;
import com.helin.springboot.repository.UserRepository;
import com.helin.springboot.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userRepository.findByUsername(registerRequest.getUsername()).isPresent()) {
            throw new RuntimeException("Kullanıcı zaten mevcut.");
        }
        User user = modelMapper.map(registerRequest, User.class);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setEmail(registerRequest.getEmail()); 
        user.setPhone(registerRequest.getPhone());

        User savedUser = userRepository.save(user);
        String token = jwtService.generateToken(savedUser);

        return new RegisterResponse("Kullanıcı baaşrıyla oluşturuldu.",token);

    }
}
