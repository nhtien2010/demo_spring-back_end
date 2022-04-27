package com.example.demo.dtos.responses;


import com.example.demo.domains.Cart;
import com.example.demo.domains.Order;
import com.example.demo.domains.UserRole;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private Date createdDate;
    private Date updatedDate;

    private String username;
    private String name;
    private String email;
    private String avatarUrl;
    private String phoneNumber;
    private String address;

    private Set<UserRole> roles;
    private CartResponseDto cart;
    private List<OrderResponseDto> orders;
}
