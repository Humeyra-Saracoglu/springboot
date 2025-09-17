package com.helin.springboot.dto.auth;

import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {

    @NotEmpty(message = "Kullanıcı adı boş olamaz")
    private String username;

    @NotEmpty(message = "Şifre boş olmaz")
    private String password;

    private String email;

    private String phone;
}
