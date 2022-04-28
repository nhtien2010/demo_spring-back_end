package com.example.demo.dtos.requests;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class OrderRequestDto {
    private String address;
    private String phoneNumber;
    private List<ShoppingProductRequestDto> shoppingProducts;
}
