package com.example.demo.services;

import com.example.demo.dtos.requests.*;
import com.example.demo.dtos.responses.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductService {
    ProductResponseDto getProduct(Long pdId);
    List<ProductResponseDto> listProducts();
    ProductResponseDto addProduct(AddProductRequestDto dto);
    Boolean deleteProduct(Long prdId);
    ProductResponseDto updateProduct(UpdateProductRequestDto dto);

    RatingResponseDto addRatingProduct(AddRatingRequestDto dto);
    RatingResponseDto updateRatingProduct(UpdateRatingRequestDto dto);
    Boolean deleteRatingProduct(Long id);
    List<RatingResponseDto> listRatingByProductId(Long productId);
}
