package com.example.demo.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter@Setter
public class UpdateProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private int inStock;
    private String brand;
    private String color;
    private String prototype;
    private String configuration;
    private List<String> imageUrls;
    private Long categoryId;
}
