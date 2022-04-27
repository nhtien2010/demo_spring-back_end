package com.example.demo.dtos.responses;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Getter@Setter
@RequiredArgsConstructor
public class ExceptionResponseDto {
    final int statusCode;
    final String message;
    final Instant timeStamp;
}
