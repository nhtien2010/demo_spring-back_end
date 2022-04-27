package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
public class Order extends BaseEntity{

    private String orderStatus;
    private Double totalPrice;
    private String address;
    private String phoneNumber;
    private String DeliveringDate;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<ShoppingProduct> shoppingProducts;

    @ManyToOne
    private UserModel userModel;

}
