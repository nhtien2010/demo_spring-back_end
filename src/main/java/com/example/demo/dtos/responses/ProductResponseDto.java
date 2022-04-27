package com.example.demo.dtos.responses;

import com.example.demo.domains.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.OneToMany;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProductResponseDto {
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    private String name;
    private String description;
    private Double price;
    private String currency;
    private int inStock;
    private String brand;

    private List<String> imageUrls;

    private Set<CategoryResponseDto> categories;
}
