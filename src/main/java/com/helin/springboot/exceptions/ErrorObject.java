package com.helin.springboot.exceptions;

import lombok.Data;

import java.util.Date;

@Data
public class ErrorObject {
    private Integer statusCode;
    private String message;
    private Date timestamp;
    private String path;
    private String method;
}