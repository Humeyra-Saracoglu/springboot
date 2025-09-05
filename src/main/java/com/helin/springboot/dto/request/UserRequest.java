package com.helin.springboot.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String username;
    private String password;
    private String email;
    private String phone;
    private String role;
    private LocalDateTime createTime;
}
