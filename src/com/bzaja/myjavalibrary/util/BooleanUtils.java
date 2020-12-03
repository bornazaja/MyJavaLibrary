package com.bzaja.myjavalibrary.util;

public final class BooleanUtils {

    private BooleanUtils() {

    }

    public static String toStringDaNe(Boolean b) {
        return toString(b, "Da", "Ne");
    }

    public static String toStringYesNo(Boolean b) {
        return toString(b, "Yes", "No");
    }

    private static String toString(Boolean b, String sYes, String sNo) {
        if (b == null) {
            return StringUtils.DEFAULT_EMPTY_VALUE;
        } else {
            return b ? sYes : sNo;
        }
    }
}
