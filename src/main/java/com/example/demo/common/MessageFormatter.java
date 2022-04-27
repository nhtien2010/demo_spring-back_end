package com.example.demo.common;

import static java.lang.String.format;

public class MessageFormatter {
    public static String formatUserNotFound(Long userId){
        return format("User %s not found", userId);
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
}
