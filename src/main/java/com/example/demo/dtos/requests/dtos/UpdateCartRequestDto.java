package com.example.demo.dtos.requests.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class UpdateCartRequestDto {
    private List<ShoppingProductRequestDto> shoppingProducts;
}
