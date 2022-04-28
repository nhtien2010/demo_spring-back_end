package com.example.demo.dtos.responses;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter@Setter
public class RatingResponseDto {
    private Long id;
    private Date createdDate;
    private Date updatedDate;
    private float rating;
    private String comment;
    private int likeNumber;
    private Long userId;
}
