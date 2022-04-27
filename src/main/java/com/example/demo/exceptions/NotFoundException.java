package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String message) {
        super(message);
    }
    final static HttpStatus status = HttpStatus.NOT_FOUND;
}
