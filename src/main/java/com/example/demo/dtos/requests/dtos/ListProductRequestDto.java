package com.example.demo.dtos.requests.dtos;

import com.example.demo.filters.search.SearchCriterion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter@Setter
public class ListProductRequestDto extends PagingRequestDto{
    private List<SearchCriterion> criteria;
}
