package com.example.demo.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter
@RequiredArgsConstructor
public class UnauthorizedRequestException extends RuntimeException{
       public UnauthorizedRequestException(String message) {
            super(message);
        }
        public final static HttpStatus status = HttpStatus.UNAUTHORIZED;
}
