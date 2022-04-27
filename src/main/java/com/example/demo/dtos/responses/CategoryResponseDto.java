package com.example.demo.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class CategoryResponseDto {
    private Long id;
    private String name;
    private String description;
}
