package com.example.demo.services;

import com.example.demo.dtos.requests.OrderRequestDto;
import com.example.demo.dtos.requests.ShoppingProductRequestDto;
import com.example.demo.dtos.requests.UpdateCartRequestDto;
import com.example.demo.dtos.responses.CartResponseDto;
import com.example.demo.dtos.responses.OrderResponseDto;
import com.example.demo.dtos.responses.ShoppingProductResponseDto;

import java.util.List;

public interface ShoppingService {
    CartResponseDto updateCart(UpdateCartRequestDto dto);
    CartResponseDto getCart(Long userId);
    OrderResponseDto getOrder(Long orderId);
    List<OrderResponseDto> getOrders(Long userId);
    CartResponseDto addProductToCart(ShoppingProductRequestDto dto);
    OrderResponseDto orderProducts(OrderRequestDto dto);

}
