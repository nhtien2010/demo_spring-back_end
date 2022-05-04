package com.example.demo.services;

import com.example.demo.dtos.requests.dtos.*;
import com.example.demo.dtos.responses.*;

import java.util.List;


public interface ProductService {
    ProductResponseDto getProduct(Long pdId);
    List<ProductResponseDto> listProducts(ListProductRequestDto dto);
    ProductResponseDto addProduct(AddProductRequestDto dto);
    Boolean deleteProduct(Long prdId);
    ProductResponseDto updateProduct(UpdateProductRequestDto dto);

    RatingResponseDto addRatingProduct(AddRatingRequestDto dto);
    RatingResponseDto updateRatingProduct(UpdateRatingRequestDto dto);
    Boolean deleteRatingProduct(Long id);
    List<RatingResponseDto> listRatingByProductId(Long productId);
}
