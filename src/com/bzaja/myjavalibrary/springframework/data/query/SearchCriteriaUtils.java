package com.bzaja.myjavalibrary.springframework.data.query;

import java.util.ArrayList;
import java.util.List;

public final class SearchCriteriaUtils {

    private SearchCriteriaUtils() {

    }

    public static List<SearchCriteriaDto> toSearchCriterias(Object value, SearchOperation searchOperation, String... columns) {
        List<SearchCriteriaDto> searchCriterias = new ArrayList<>();
        for (String column : columns) {
            searchCriterias.add(new SearchCriteriaDto(SearchCriteriaType.INCLUSIVE, column, searchOperation, value));
        }
        return searchCriterias;
    }
}
