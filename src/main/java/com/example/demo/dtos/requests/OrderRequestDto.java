package com.example.demo.dtos.requests;

import java.util.List;

public class OrderRequestDto {
    private String address;
    private String phoneNumber;
    private List<ShoppingProductRequestDto> shoppingProducts;
}
