package com.example.demo.services.impls;

import com.example.demo.common.MessageFormatter;
import com.example.demo.common.UserRoleEnum;
import com.example.demo.domains.Category;
import com.example.demo.domains.UserModel;
import com.example.demo.domains.UserRole;
import com.example.demo.dtos.requests.AddCategoryRequestDto;
import com.example.demo.dtos.requests.RegisterAdminRequestDto;
import com.example.demo.dtos.requests.UpdateCategoryRequestDto;
import com.example.demo.dtos.responses.CartResponseDto;
import com.example.demo.dtos.responses.CategoryResponseDto;
import com.example.demo.dtos.responses.UserResponseDto;
import com.example.demo.exceptions.BadRequestException;
import com.example.demo.exceptions.ConflictRequestException;
import com.example.demo.exceptions.NotFoundException;
import com.example.demo.repositories.CategoryRepository;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
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
    public Boolean isCategoryExist(String name){
        return categoryRepository.findByName(name).isPresent();
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
        boolean isExisted = isCategoryExist(dto.getName());
        if(isExisted){
            throw new ConflictRequestException(MessageFormatter.formatCategoryAlreadyExist(dto.getName()));
        }
        Category category = mapper.map(dto, Category.class);
        category.setCreatedDate();
        category.setUpdatedDate();
        categoryRepository.save(category);
        return mapper.map(category, CategoryResponseDto.class);
    }

    @Override
    public CategoryResponseDto getCategory(Long id) {
        return mapper.map(getCategoryById(id), CategoryResponseDto.class);
    }

    @Override
    public Boolean deleteCategory(Long catId) {
        getCategoryById(catId);
        categoryRepository.deleteById(catId);
        return true;
    }

    @Override
    public CategoryResponseDto updateCategory(UpdateCategoryRequestDto dto) {
        getCategoryById(dto.getId());
        Category update = mapper.map(dto, Category.class);
        update.setUpdatedDate();
        categoryRepository.save(update);
        return mapper.map(update, CategoryResponseDto.class);
    }
}
