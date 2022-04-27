package com.example.demo.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@RequiredArgsConstructor
public class ForbiddenRequestException extends RuntimeException {
    public ForbiddenRequestException(String message) {
        super(message);
    }

    final static HttpStatus status = HttpStatus.FORBIDDEN;
}
