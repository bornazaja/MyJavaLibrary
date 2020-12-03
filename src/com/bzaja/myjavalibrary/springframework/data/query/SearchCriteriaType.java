/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bzaja.myjavalibrary.springframework.data.query;

import com.bzaja.myjavalibrary.util.Descriptable;

/**
 *
 * @author Borna
 */
public enum SearchCriteriaType implements Descriptable {
    INCLUSIVE("Inclusive"),
    NOT_INCLUSIVE("Not inclusive");

    private String description;

    private SearchCriteriaType(String description) {
        this.description = description;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
