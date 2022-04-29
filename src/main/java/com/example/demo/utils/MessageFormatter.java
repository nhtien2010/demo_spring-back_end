package com.example.demo.utils;

import static java.lang.String.format;

public class MessageFormatter {
    public static String formatUserNotFound(Long userId){
        return format("User id %s not found", userId);
    }
    public static String formatUserNotFound(String username){
        return format("User %s not found", username);
    }
    public static String formatUserAlreadyExist(String username){
        return format("User %s already exists", username);
    }
    public static String formatInvalidRequestInput(String input){
        return format("Invalid request input - %s", input);
    }
    public static String formatCategoryNotFound(Long id){ return format("Category id %s not found", id); }
    public static String formatCategoryAlreadyExist(String name){
        return format("Category %s already exists", name);
    }
    public static String formatProductNotFound(Long id){ return format("Product id %s not found", id); }
    public static String formatRatingNotFound(Long userId, Long productId){ return format("Rating with (product_id: %s, user_id: %s) not found", productId, userId); }
    public static String formatUserAlreadyRatingProduct(Long userId, Long productId){ return format("Rating with (product_id: %s, user_id: %s) already exists", productId, userId); }
    public static String formatRatingNotFound(Long id){ return format("Rating id %s not found", id); }
    public static String formatUnprivilegedRequest(String action){ return format("Request unprivileged for: %s", action); }
}
