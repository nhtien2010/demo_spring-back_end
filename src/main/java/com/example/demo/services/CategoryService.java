package com.example.demo.services;

import com.example.demo.domains.Category;
import com.example.demo.dtos.requests.AddCategoryRequestDto;
import com.example.demo.dtos.requests.UpdateCategoryRequestDto;
import com.example.demo.dtos.responses.CategoryResponseDto;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDto> getAllCategories();
    CategoryResponseDto addCategory(AddCategoryRequestDto dto);
    CategoryResponseDto getCategory(Long id);
    Boolean deleteCategory(Long catId);
    CategoryResponseDto updateCategory(UpdateCategoryRequestDto dto);
}
