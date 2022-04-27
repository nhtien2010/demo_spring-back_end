package com.example.demo.configs;

public class JWTConfig {
    public static final String secretKey = "zrURoYeKwW0Lfae4ckcTMbf9Hw0Bs57GQg9rKEbdoD6bjDJexMdRQv8AJ0EmVxoPQbkYHLZ18aT9w6cu";
    public static final String audience = "demo";
    public static final String issuer = "demo";
    public static final int tokenAccessMinutes = 120;
    public static final int tokenRefreshMinutes = 1440;
}
