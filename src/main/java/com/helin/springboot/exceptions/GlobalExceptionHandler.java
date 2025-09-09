package com.helin.springboot.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Date;


@RestControllerAdvice
public class GlobalExceptionHandler {

    // 404 not found hatası
    @ExceptionHandler(UserPrincipalNotFoundException.class)
    public ResponseEntity<ErrorObject> handleUserPrincipalNotFound(HttpServletRequest req ) {
        return build(HttpStatus.NOT_FOUND, "Yapılan istek bulunamadı", req);
    }

    // 401 hatası - Yetkisiz
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorObject> handleAuth( HttpServletRequest req ) {
        return build(HttpStatus.UNAUTHORIZED, "Kimlik doğrulaması gerekli", req);
    }

    // 403 hatası - Yetki yok
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorObject> handleDenied(HttpServletRequest req ) {
        return build(HttpStatus.FORBIDDEN, "Bu işlem için yetkiniz yok", req);
    }

    // 500 hatası - Genel
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorObject> handleGeneric(HttpServletRequest req ) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, "Bir şeyler ters gitti", req);
    }

    // ortak cevap fonksiyonu
    private ResponseEntity<ErrorObject> build(HttpStatus status, String message, HttpServletRequest req) {
        ErrorObject errorObject = new ErrorObject();

        errorObject.setStatusCode(status.value());
        errorObject.setMessage(message);
        errorObject.setTimestamp(new Date());
        errorObject.setPath(req.getRequestURI());
        errorObject.setMethod(req.getMethod());

        return ResponseEntity.status(status).body(errorObject);
    }


}
