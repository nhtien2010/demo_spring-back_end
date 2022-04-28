package com.example.demo.dtos.requests;

import com.example.demo.dtos.responses.ProductResponseDto;
import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class ShoppingProductRequestDto {
    private Long id;
    private Long quantity;
}
