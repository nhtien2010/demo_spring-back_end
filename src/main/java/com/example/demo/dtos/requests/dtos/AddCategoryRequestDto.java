package com.example.demo.dtos.requests.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class AddCategoryRequestDto {
    private String name;
    private String description;
}
