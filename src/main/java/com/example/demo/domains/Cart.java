package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "cart")
public class Cart extends  BaseEntity {

    private Double totalPrice;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ShoppingProduct> shoppingProducts;

    @OneToOne(cascade = CascadeType.ALL)
    private UserModel userModel;
}
