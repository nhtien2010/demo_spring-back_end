package com.example.demo.services.impls;

import com.example.demo.common.MessageFormatter;
import com.example.demo.domains.Category;
import com.example.demo.domains.UserModel;
import com.example.demo.dtos.requests.AddCategoryRequestDto;
import com.example.demo.dtos.requests.UpdateCategoryRequestDto;
import com.example.demo.dtos.responses.CartResponseDto;
import com.example.demo.dtos.responses.CategoryResponseDto;
import com.example.demo.dtos.responses.UserResponseDto;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;

    private Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        MessageFormatter.formatCategoryNotFound(id)));
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream()
                .map(entity -> mapper.map(entity, CategoryResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponseDto addCategory(AddCategoryRequestDto dto) {
        return null;
    }

    @Override
    public Boolean deleteCategory(Long catId) {
        getCategoryById(catId);
        categoryRepository.deleteById(catId);
        return true;
    }

    @Override
    public CategoryResponseDto updateCategory(UpdateCategoryRequestDto dto) {
        return null;
    }
}
