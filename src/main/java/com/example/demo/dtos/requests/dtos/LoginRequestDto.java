package com.example.demo.dtos.requests.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequestDto {
    public String username;
    public String password;
}
