package com.example.demo.dtos.responses;

import com.example.demo.domains.ShoppingProduct;
import com.example.demo.domains.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.Date;
import java.util.List;

public class CartResponseDto {
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    private Double totalPrice;

    private List<ShoppingProductResponseDto> shoppingProducts;

}
