package com.example.demo.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class RegisterRequestDto {
    public String name;
    public String email;
    public String username;
    public String password;
}
