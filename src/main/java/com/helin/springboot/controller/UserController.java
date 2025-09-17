package com.helin.springboot.controller;

import com.helin.springboot.dto.auth.RegisterRequest;
import com.helin.springboot.dto.auth.RegisterResponse;
import com.helin.springboot.dto.request.UserRequest;
import com.helin.springboot.dto.response.UserResponse;
import com.helin.springboot.service.AuthService;
import com.helin.springboot.service.UserService;
import com.helin.springboot.service.impl.AuthServiceImpl;
import com.helin.springboot.service.impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServiceImpl userServiceImpl;
    private final UserService userService;
    private final AuthServiceImpl authServiceImpl;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@Valid @RequestBody UserRequest request) {
        logger.info("User created");
        return userService.createUser(request);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
        RegisterResponse response = authServiceImpl.register(request);
        return new  ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public UserResponse read(@PathVariable Long id) {
        return userService.readUser(id);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @Valid @RequestBody UserRequest request) {
        return userService.updateUser(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }


}
