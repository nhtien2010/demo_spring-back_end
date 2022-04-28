package com.example.demo.dtos.requests;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class UpdateRatingRequestDto {
    private Long id;
    private float rating;
    private String comment;
}
