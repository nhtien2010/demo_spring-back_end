package com.example.demo.services.impls;

import com.example.demo.dtos.requests.AddCategoryRequestDto;
import com.example.demo.dtos.requests.AddProductRequestDto;
import com.example.demo.dtos.requests.UpdateCategoryRequestDto;
import com.example.demo.dtos.requests.UpdateProductRequestDto;
import com.example.demo.dtos.responses.CategoryResponseDto;
import com.example.demo.dtos.responses.ProductResponseDto;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductResponseDto getProduct(Long pdId) {
        return null;
    }

    @Override
    public List<ProductResponseDto> listProducts() {
        return null;
    }

    @Override
    public ProductResponseDto addProduct(AddProductRequestDto dto) {
        return null;
    }

    @Override
    public Boolean deleteProduct(Long prdId) {
        return null;
    }

    @Override
    public ProductResponseDto updateProduct(UpdateProductRequestDto dto) {
        return null;
    }

}
