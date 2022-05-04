package com.example.demo.dtos.requests.dtos;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Min;

@Getter@Setter
public class AddRatingRequestDto {
    @Range(max = 5)
    private float rating;
    private String comment;
    private Long productId;
}
