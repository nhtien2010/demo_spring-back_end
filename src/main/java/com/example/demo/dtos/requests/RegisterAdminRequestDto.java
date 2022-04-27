package com.example.demo.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class RegisterAdminRequestDto extends RegisterUserRequestDto{
    public String userRole; //ADMIN
}
