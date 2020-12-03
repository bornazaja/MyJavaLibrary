package com.bzaja.myjavalibrary.springframework.data.query;

import com.bzaja.myjavalibrary.util.Descriptable;

public enum SearchOperation implements Descriptable{

    LESS_THAN("Less"),
    LESS_THAN_OR_EQUAL_TO("Less than or equal to"),
    EQUAL("Equal"),
    STARTS_WITH("Starts with"),
    CONTAINS("Contains"),
    ENDS_WITH("Ends with"),
    GREATER_THEN("Greater then"),
    GREATER_THEN_OR_EQUAL_TO("Greater then or equal to");

    private String description;

    private SearchOperation(String description) {
        this.description = description;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
}
