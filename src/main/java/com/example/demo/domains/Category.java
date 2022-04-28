package com.example.demo.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category extends BaseEntity{
    private String name;
    private String description;

    @OneToMany(mappedBy = "category")
    private Set<Product> products;
}
