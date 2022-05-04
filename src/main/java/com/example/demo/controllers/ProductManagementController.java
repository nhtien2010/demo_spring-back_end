package com.example.demo.controllers;

import com.example.demo.dtos.requests.dtos.*;
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
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public ResponseEntity<?> listProducts(@Valid @RequestBody ListProductRequestDto request){
        return ResponseEntity.ok(productService.listProducts(request));
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addProduct(@Valid @RequestBody AddProductRequestDto request){
        return ResponseEntity.ok(productService.addProduct(request));
    }
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@Valid @RequestBody UpdateProductRequestDto request){
        return ResponseEntity.ok(productService.updateProduct(request));
    }
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@Valid @RequestParam("prId") Long prId){
        return ResponseEntity.ok(productService.deleteProduct(prId));
    }

    @RequestMapping(value = "/{prId}/Rating/list", method = RequestMethod.GET)
    public ResponseEntity<?> listProductRatings(@Valid @PathVariable Long prId){
        return ResponseEntity.ok(productService.listRatingByProductId(prId));
    }

    @RequestMapping(value = "/Rating/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRating(@Valid @RequestBody AddRatingRequestDto request){
        return ResponseEntity.ok(productService.addRatingProduct(request));
    }
    @RequestMapping(value = "/Rating/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateRating(@Valid @RequestBody UpdateRatingRequestDto request){
        return ResponseEntity.ok(productService.updateRatingProduct(request));
    }
    @RequestMapping(value = "/Rating/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteRating(@Valid @RequestParam("ratingId") Long ratingId){
        return ResponseEntity.ok(productService.deleteRatingProduct(ratingId));
    }

}
