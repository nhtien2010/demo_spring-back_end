package com.example.demo.services;

import com.example.demo.dtos.requests.dtos.LoginRequestDto;
import com.example.demo.dtos.requests.dtos.RegisterRequestDto;
import com.example.demo.dtos.responses.LoginResponseDto;

public interface AuthenticateService {
    LoginResponseDto authenticate(LoginRequestDto RequestDto);
    Boolean register(RegisterRequestDto request);
    Boolean logOut(Long userId);

}
