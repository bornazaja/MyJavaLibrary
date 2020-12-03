package com.bzaja.myjavalibrary.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class LocalDateUtils {

    private LocalDateUtils() {

    }

    public static String format(LocalDate localDate, LocalDatePattern localDatePattern) {
        return localDate != null ? localDate.format(DateTimeFormatter.ofPattern(localDatePattern.getPattern())) : StringUtils.DEFAULT_EMPTY_VALUE;
    }

    public static LocalDatePattern getLocalDatePattern(LanguageFormat languageFormat) {
        switch (languageFormat) {
            case EN:
                return LocalDatePattern.ISO_8601;
            case HR:
                return LocalDatePattern.HR;
            default:
                throw new RuntimeException("This Language format does not exists.");
        }
    }
}
