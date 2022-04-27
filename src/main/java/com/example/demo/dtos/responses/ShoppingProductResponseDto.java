package com.example.demo.dtos.responses;

import com.example.demo.domains.Cart;
import com.example.demo.domains.Order;
import com.example.demo.domains.Product;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

public class ShoppingProductResponseDto {
    private Long quantity;
    private Double totalPrice;

    private ProductResponseDto product;
}
