package com.helin.springboot.service;

import com.helin.springboot.dto.auth.RegisterRequest;
import com.helin.springboot.dto.auth.RegisterResponse;

public interface AuthService {

    RegisterResponse register(RegisterRequest registerRequest);
}
