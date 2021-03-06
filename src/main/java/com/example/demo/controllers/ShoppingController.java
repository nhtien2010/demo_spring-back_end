package com.example.demo.controllers;

import com.example.demo.dtos.requests.dtos.OrderRequestDto;
import com.example.demo.dtos.requests.dtos.ShoppingProductRequestDto;
import com.example.demo.dtos.requests.dtos.UpdateCartRequestDto;
import com.example.demo.services.ShoppingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/shopping")
@RequiredArgsConstructor
public class ShoppingController {
    private final ShoppingService shoppingService;

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@Valid @RequestBody ShoppingProductRequestDto dto){
        return ResponseEntity.ok(shoppingService.addProductToCart(dto));
    }
    @RequestMapping(value = "/updateCart", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCart(@Valid @RequestBody UpdateCartRequestDto dto){
        return ResponseEntity.ok(shoppingService.updateCart(dto));
    }

    @RequestMapping(value = "/getCart", method = RequestMethod.GET)
    public ResponseEntity<?> getCart(){
        return ResponseEntity.ok(shoppingService.getCart());
    }


    @RequestMapping(value = "/getOrder", method = RequestMethod.GET)
    public ResponseEntity<?> getOrder(@Valid @RequestParam("orderId") Long orderId) {
        return ResponseEntity.ok(shoppingService.getOrder(orderId));
    }
    @RequestMapping(value = "/listOrders", method = RequestMethod.GET)
    public ResponseEntity<?> getOrders(){
        return ResponseEntity.ok(shoppingService.getOrders());
    }
    @RequestMapping(value = "/ordering", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@Valid @RequestBody OrderRequestDto dto){
        return ResponseEntity.ok(shoppingService.orderProducts(dto));
    }

}
