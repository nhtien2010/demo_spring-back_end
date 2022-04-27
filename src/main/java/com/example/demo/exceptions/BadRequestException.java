package com.example.demo.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter@Setter@RequiredArgsConstructor
public class BadRequestException extends RuntimeException{
//    final private String message;
    public BadRequestException(String message) {
    super(message);
}
    final static HttpStatus status = HttpStatus.BAD_REQUEST;
}
