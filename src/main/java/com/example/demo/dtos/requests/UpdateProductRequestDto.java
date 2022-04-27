package com.example.demo.dtos.requests;

import java.util.List;
import java.util.Set;

public class UpdateProductRequestDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private int inStock;
    private String brand;
    private List<String> imageUrls;
    private Set<Long> categoryIds;
}
