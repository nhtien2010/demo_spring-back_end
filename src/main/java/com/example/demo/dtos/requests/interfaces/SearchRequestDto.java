package com.example.demo.dtos.requests.interfaces;

import java.util.HashMap;
import java.util.Map;

public interface SearchRequestDto {
    public Map<String, String> getAcceptedField();
    public Boolean isValidCriteria();
}
