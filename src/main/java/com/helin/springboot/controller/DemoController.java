package com.helin.springboot.controller;

import com.helin.springboot.exceptions.AccessDeniedException;
import com.helin.springboot.exceptions.UserPrincipalNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v2/demo")
public class DemoController {

    // 404
    @GetMapping("/not-found/{id}")
    public String notFound(@PathVariable Long id) {
        log.info("GET /api/demo/not-found/{} çağrıldı", id);
        log.warn("Kullanıcı bulunamadı uyarısı tetiklenecek id={}", id);
        throw new UserPrincipalNotFoundException("Kullanıcı bulunamadı: " + id);
    }

    // 401
    @GetMapping("/unauth")
    public String unauth() {
        log.debug("Kimlik doğrulama testi endpoint'i");
        throw new BadCredentialsException("Geçersiz/eksik kimlik bilgisi");
    }

    // 403
    @GetMapping("/denied")
    public String denied() {
        log.debug("Yetki testi endpoint'i");
        throw new AccessDeniedException("Bu işlem için yetkin yok");
    }

    // 500
    @GetMapping("/error")
    public String error() {
        log.info("Genel hata testi tetiklendi");
        throw new RuntimeException("Deneme hatası");
    }
}
