package com.example.demo.common;

public enum SearchOperation {
    EQUALITY(":"), GREATER_THAN(">"), LESS_THAN("<"), CONTAINS("%");

    private final String operation;
    SearchOperation(final String text) {
        this.operation = text;
    }
    @Override
    public String toString() {
        return operation;
    }
}
