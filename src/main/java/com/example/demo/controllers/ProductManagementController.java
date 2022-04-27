package com.example.demo.controllers;

import com.example.demo.dtos.requests.AddProductRequestDto;
import com.example.demo.dtos.requests.RegisterRequestDto;
import com.example.demo.dtos.requests.UpdateProductRequestDto;
import com.example.demo.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/product")
@RequiredArgsConstructor
public class ProductManagementController {
    private final ProductService productService;

    @RequestMapping(value = "/{prId}", method = RequestMethod.GET)
    public ResponseEntity<?> getProduct(@Valid @PathVariable Long prId){
        return ResponseEntity.ok(productService.getProduct(prId));
    }
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ResponseEntity<?> listProducts(){
        return ResponseEntity.ok(productService.listProducts());
    }

    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequestDto request){
        return ResponseEntity.ok(productService.addProduct(request));
    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductRequestDto request){
        return ResponseEntity.ok(productService.updateProduct(request));
    }
    @PreAuthorize(value = "hasRole('ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@Valid @RequestParam("prId") Long prId){
        return ResponseEntity.ok(productService.deleteProduct(prId));
    }

}
