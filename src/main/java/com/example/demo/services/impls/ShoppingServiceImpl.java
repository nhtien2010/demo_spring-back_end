package com.example.demo.services.impls;

import com.example.demo.dtos.requests.OrderRequestDto;
import com.example.demo.dtos.requests.ShoppingProductRequestDto;
import com.example.demo.dtos.requests.UpdateCartRequestDto;
import com.example.demo.dtos.responses.CartResponseDto;
import com.example.demo.dtos.responses.OrderResponseDto;
import com.example.demo.services.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ShoppingServiceImpl implements ShoppingService {
    @Override
    public CartResponseDto updateCart(UpdateCartRequestDto dto) {
        return null;
    }

    @Override
    public CartResponseDto getCart(Long userId) {
        return null;
    }

    @Override
    public OrderResponseDto getOrder(Long orderId) {
        return null;
    }

    @Override
    public List<OrderResponseDto> getOrders(Long userId) {
        return null;
    }

    @Override
    public CartResponseDto addProductToCart(ShoppingProductRequestDto dto) {
        return null;
    }

    @Override
    public OrderResponseDto orderProducts(OrderRequestDto dto) {
        return null;
    }
}
