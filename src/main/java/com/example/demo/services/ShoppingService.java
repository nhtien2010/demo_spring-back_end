package com.example.demo.services;

import com.example.demo.dtos.requests.dtos.OrderRequestDto;
import com.example.demo.dtos.requests.dtos.ShoppingProductRequestDto;
import com.example.demo.dtos.requests.dtos.UpdateCartRequestDto;
import com.example.demo.dtos.responses.CartResponseDto;
import com.example.demo.dtos.responses.OrderResponseDto;

import java.util.List;

public interface ShoppingService {
    CartResponseDto updateCart(UpdateCartRequestDto dto);
    CartResponseDto getCart(Long cartId);
    CartResponseDto getCart();
    OrderResponseDto getOrder(Long orderId);
    List<OrderResponseDto> getOrders();
    CartResponseDto addProductToCart(ShoppingProductRequestDto dto);
    OrderResponseDto orderProducts(OrderRequestDto dto);

}
