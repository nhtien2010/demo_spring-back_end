package com.example.demo.services.impls;

import com.example.demo.dtos.requests.AddCategoryRequestDto;
import com.example.demo.dtos.requests.UpdateCategoryRequestDto;
import com.example.demo.dtos.responses.CategoryResponseDto;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<CategoryResponseDto> getAllCategories() {
        return null;
    }

    @Override
    public CategoryResponseDto addCategory(AddCategoryRequestDto dto) {
        return null;
    }

    @Override
    public Boolean deleteCategory(Long catId) {
        return null;
    }

    @Override
    public CategoryResponseDto updateCategory(UpdateCategoryRequestDto dto) {
        return null;
    }
}
