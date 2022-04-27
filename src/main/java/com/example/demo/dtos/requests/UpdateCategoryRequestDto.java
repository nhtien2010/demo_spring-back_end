package com.example.demo.dtos.requests;

import lombok.Getter;

@Getter
public class UpdateCategoryRequestDto {
    private Long id;
    private String name;
    private String description;
}
