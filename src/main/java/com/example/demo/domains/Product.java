package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Getter
@Setter
public class Product extends BaseEntity {

    private String name;
    private String description;
    private Double price;
    private String currency;
    private int inStock;
    private String brand;

    @Transient
    private List<String> imageUrls;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private Set<Category> categories;


}
