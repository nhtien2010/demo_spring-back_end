package com.example.demo.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Rating extends BaseEntity{
    private float rating;
    private String comment;
    private int likeNumber;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserModel userModel;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
