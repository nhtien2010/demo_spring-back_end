package com.example.demo.dtos.requests.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateCategoryRequestDto {
    private Long id;
    private String name;
    private String description;
}
