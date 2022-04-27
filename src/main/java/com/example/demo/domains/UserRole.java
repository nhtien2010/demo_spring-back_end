package com.example.demo.domains;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
public class UserRole implements GrantedAuthority {
    //public static final String CUSTOMER = "CUSTOMER";
    //public static final String ADMIN = "ADMIN";
    private String authority;

    public UserRole(String authority) {
        this.authority = authority;
    }
}
