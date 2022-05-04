package com.example.demo.controllers;

import com.example.demo.dtos.requests.dtos.AddCategoryRequestDto;
import com.example.demo.dtos.requests.dtos.UpdateCategoryRequestDto;
import com.example.demo.services.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/api/category")
@RequiredArgsConstructor
public class CategoryManagementController {
    private final CategoryService categoryService;

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCategories(){
        return ResponseEntity.ok(categoryService.getAllCategories());
    }

    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResponseEntity<?> addCategory(@Valid @RequestBody AddCategoryRequestDto request){
        return ResponseEntity.ok(categoryService.addCategory(request));
    }
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<?> updateCategory(@Valid @RequestBody UpdateCategoryRequestDto request){
        return ResponseEntity.ok(categoryService.updateCategory(request));
    }
    @PreAuthorize(value = "hasAuthority('ADMIN')")
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity<?> getAllCategories(@Valid @RequestParam("catId") Long catId){
        return ResponseEntity.ok(categoryService.deleteCategory(catId));
    }
}
