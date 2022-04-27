package com.example.demo.dtos.requests;

import com.example.demo.domains.Category;

import java.util.List;
import java.util.Set;

public class AddProductRequestDto {
    private String name;
    private String description;
    private Double price;
    private String currency;
    private int inStock;
    private String brand;
    private List<String> imageUrls;
    private Set<Long> categoryIds;
}
