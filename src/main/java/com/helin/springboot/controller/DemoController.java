package com.helin.springboot.controller;

import com.helin.springboot.exceptions.AccessDeniedException;
import com.helin.springboot.exceptions.UserPrincipalNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class DemoController {

    // 404
    @GetMapping("/not-found/{id}")
    public String notFound(@PathVariable Long id) {
        throw new UserPrincipalNotFoundException("Kullanıcı bulunamadı: " + id);
    }

    // 401
    @GetMapping("/unauth")
    public String unauth() {
        throw new BadCredentialsException("Geçersiz/eksik kimlik bilgisi");
    }

    // 403
    @GetMapping("/denied")
    public String denied() {
        throw new AccessDeniedException("Bu işlem için yetkin yok");
    }

    // 500
    @GetMapping("/error")
    public String error() {
        throw new RuntimeException("Deneme hatası");
    }
}
