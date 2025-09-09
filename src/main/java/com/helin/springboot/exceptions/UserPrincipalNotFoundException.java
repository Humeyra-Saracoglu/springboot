package com.helin.springboot.exceptions;

public class UserPrincipalNotFoundException extends RuntimeException {

    public UserPrincipalNotFoundException(String message) {
        super(message);
    }
}
