package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;

public class ConflictRequestException extends RuntimeException{
        public ConflictRequestException(String message) {
            super(message);
        }
        final static HttpStatus status = HttpStatus.CONFLICT;

}
