package com.example.demo.dtos.responses;

import com.example.demo.domains.ShoppingProduct;
import com.example.demo.domains.UserModel;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Getter
@Setter
public class OrderResponseDto {
    private String orderStatus;
    private Double totalPrice;
    private String address;
    private String phoneNumber;
    private String DeliveringDate;

    private List<ShoppingProductResponseDto> shoppingProducts;

}
