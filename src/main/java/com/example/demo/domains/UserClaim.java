package com.example.demo.domains;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UserClaim  extends BaseEntity {
    private String token;
    private Long userId;
}
