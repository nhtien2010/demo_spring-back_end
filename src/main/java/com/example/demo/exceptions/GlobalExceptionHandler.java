package com.example.demo.exceptions;

import com.example.demo.dtos.responses.ExceptionResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler  {
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private ResponseEntity<ExceptionResponseDto> buildResponse(HttpStatus status, String message){
        logger.error("Handler exception : {}", message);
        return new ResponseEntity<>(
                new ExceptionResponseDto(status.value(), message, Instant.now()), status);
    }

    @ExceptionHandler(value = {BadRequestException.class})
    public final ResponseEntity<ExceptionResponseDto> handleBadRequestException(RuntimeException ex, WebRequest request) {
        return buildResponse(BadRequestException.status, ex.getMessage());
    }

    @ExceptionHandler(value = {NotFoundException.class, UsernameNotFoundException.class})
    public final ResponseEntity<ExceptionResponseDto> handleNotFoundException(RuntimeException ex, WebRequest request) {
        return buildResponse(NotFoundException.status, ex.getMessage());
    }

    @ExceptionHandler(value = {ConflictRequestException.class})
    public final ResponseEntity<ExceptionResponseDto> handleConflictRequestException(RuntimeException ex, WebRequest request) {
        return buildResponse(ConflictRequestException.status, ex.getMessage());
    }


    @ExceptionHandler(value = {RuntimeException.class})
    protected ResponseEntity<Object> handleRemainExceptions(RuntimeException ex){
        logger.error("Remain exception : {}", ex.getMessage());
        return new ResponseEntity<>(ex, HttpStatus.valueOf(400));
    }


}
