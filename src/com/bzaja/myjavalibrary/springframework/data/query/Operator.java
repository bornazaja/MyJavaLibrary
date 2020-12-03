package com.bzaja.myjavalibrary.springframework.data.query;

import com.bzaja.myjavalibrary.util.Descriptable;

public enum Operator implements Descriptable {

    AND("AND"),
    OR("OR");

    private String description;

    private Operator(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
