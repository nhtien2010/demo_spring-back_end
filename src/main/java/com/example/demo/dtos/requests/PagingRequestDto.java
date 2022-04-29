package com.example.demo.dtos.requests;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

@Getter@Setter
public abstract class PagingRequestDto {
    @Range(min = 1, max = 1000)
    private int page;
    @Range(min = 1, max = 20)
    private int limit;
    private Boolean isAscending;
    private String sortBy;
}
