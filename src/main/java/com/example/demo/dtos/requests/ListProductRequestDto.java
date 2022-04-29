package com.example.demo.dtos.requests;

import com.example.demo.filters.search.SearchCriterion;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter@Setter
public class ListProductRequestDto extends PagingRequestDto{
    private List<SearchCriterion> criteria;
}
