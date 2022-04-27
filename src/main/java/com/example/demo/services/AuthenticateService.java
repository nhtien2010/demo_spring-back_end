package com.example.demo.services;

import com.example.demo.dtos.requests.LoginRequestDto;
import com.example.demo.dtos.requests.RegisterAdminRequestDto;
import com.example.demo.dtos.requests.RegisterRequestDto;
import com.example.demo.dtos.requests.RegisterUserRequestDto;
import com.example.demo.dtos.responses.LoginResponseDto;
import com.example.demo.dtos.responses.UserResponseDto;

public interface AuthenticateService {
    LoginResponseDto authenticate(LoginRequestDto RequestDto);
    Boolean register(RegisterRequestDto request);
    Boolean logOut(Long userId);

}
