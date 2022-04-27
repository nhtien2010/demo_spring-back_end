package com.example.demo.services;

import com.example.demo.dtos.requests.RegisterRequestDto;
import com.example.demo.dtos.requests.UpdateUserRequestDto;
import com.example.demo.dtos.responses.UserResponseDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService{

    Boolean lockUser(Long userId);
    Boolean unlockUser(Long userId);
    UserResponseDto getUser(Long userId);
    List<UserResponseDto> getUsers();
    UserResponseDto createUser(RegisterRequestDto dto);
    Boolean deleteUser(Long userId);
    UserResponseDto updateUser(UpdateUserRequestDto userRequest);
    Boolean isUserExist(String username);
}
