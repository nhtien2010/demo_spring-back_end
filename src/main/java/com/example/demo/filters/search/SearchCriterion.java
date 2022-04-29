package com.example.demo.filters.search;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class SearchCriterion {
    private String field;
    private String operation;
    private Object value;
}
