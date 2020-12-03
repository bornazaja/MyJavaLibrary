package com.bzaja.myjavalibrary.util;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

public final class BeanUtils {

    private BeanUtils() {

    }

    public static Object getPropertyValue(Object object, String propertyName, Object defaultValue) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        try {
            Object value = PropertyUtils.getNestedProperty(object, propertyName);
            return value != null ? value : defaultValue;
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NestedNullException ex) {
            return defaultValue;
        }
    }

    public static Object getPropertyValue(Object object, String propertyName) {
        return getPropertyValue(object, propertyName, null);
    }

    public static String getFormattedPropertyValue(Object object, String propertyName, String defaultValue, LanguageFormat languageFormat) {
        if (object == null) {
            throw new IllegalArgumentException("Object cannot be null");
        }

        try {
            int plusCharCount = org.apache.commons.lang3.StringUtils.countMatches(propertyName, '+');

            if (plusCharCount > 0) {
                StringBuilder stringBuilder = new StringBuilder();
                String[] propertyNames = propertyName.split("\\+");
                for (int i = 0; i < propertyNames.length; i++) {
                    if (i == propertyNames.length - 1) {
                        stringBuilder.append(getFormatedValue(object, propertyNames[i], defaultValue, languageFormat));
                    } else {
                        stringBuilder.append(getFormatedValue(object, propertyNames[i], defaultValue, languageFormat)).append(" ");
                    }
                }
                return stringBuilder.toString();
            } else {
                return getFormatedValue(object, propertyName, defaultValue, languageFormat);
            }
        } catch (IllegalAccessException | InvocationTargetException | NoSuchMethodException | NestedNullException ex) {
            return StringUtils.DEFAULT_EMPTY_VALUE;
        }
    }

    private static String getFormatedValue(Object object, String propertyName, String defaultValue, LanguageFormat languageFormat) throws InvocationTargetException, IllegalAccessException, IllegalArgumentException, NoSuchMethodException {
        Object value = PropertyUtils.getNestedProperty(object, propertyName);
        Class<?> type = PropertyUtils.getPropertyType(object, propertyName);

        if (value != null) {
            if (type.isAssignableFrom(String.class)) {
                return value.toString();
            } else if (type.isAssignableFrom(Integer.class)) {
                return value.toString();
            } else if (type.isAssignableFrom(Double.class)) {
                return getNumberAsString(value, languageFormat);
            } else if (type.isAssignableFrom(Boolean.class)) {
                return getBooleanAsString(value, languageFormat);
            } else if (type.isAssignableFrom(LocalDate.class)) {
                return getLocalDateAsString(value, languageFormat);
            } else if (type.isAssignableFrom(LocalDateTime.class)) {
                return getLocalDateTimeAsString(value, languageFormat);
            } else {
                throw new IllegalArgumentException("This type is not supported.");
            }
        } else {
            return defaultValue != null ? defaultValue : StringUtils.DEFAULT_EMPTY_VALUE;
        }
    }

    private static String getNumberAsString(Object value, LanguageFormat languageFormat) {
        switch (languageFormat) {
            case EN:
                return value.toString();
            case HR:
                return NumberUtils.format(Double.parseDouble(value.toString()), NumberFormatPatterns.DECIMAL_HR);
            default:
                throw new RuntimeException("This language format does not exists.");
        }
    }

    private static String getBooleanAsString(Object value, LanguageFormat languageFormat) {
        switch (languageFormat) {
            case EN:
                return BooleanUtils.toStringYesNo(Boolean.valueOf(value.toString()));
            case HR:
                return BooleanUtils.toStringDaNe(Boolean.valueOf(value.toString()));
            default:
                throw new RuntimeException("This language format does not exists.");
        }
    }

    private static String getLocalDateAsString(Object value, LanguageFormat languageFormat) {
        switch (languageFormat) {
            case EN:
                return LocalDateUtils.format(LocalDate.parse(value.toString()), LocalDatePattern.ISO_8601);
            case HR:
                return LocalDateUtils.format(LocalDate.parse(value.toString()), LocalDatePattern.HR);
            default:
                throw new RuntimeException("This language format does not exists.");
        }
    }

    private static String getLocalDateTimeAsString(Object value, LanguageFormat languageFormat) {
        switch (languageFormat) {
            case EN:
                return LocalDateTimeUtils.format(LocalDateTime.parse(value.toString()), LocalDateTimePattern.ISO_8601);
            case HR:
                return LocalDateTimeUtils.format(LocalDateTime.parse(value.toString()), LocalDateTimePattern.HR);
            default:
                throw new RuntimeException("This language format does not exists.");
        }
    }

    public static String getFormattedPropertyValue(Object object, String propertyName, LanguageFormat languageFormat) {
        return getFormattedPropertyValue(object, propertyName, null, languageFormat);
    }

    public static boolean isPropertyTextType(Class<?> clazz, String propertyName) {
        Class<?> propertyType = getPropertyTypeByClassAndPropertyName(clazz, propertyName);
        return propertyType.isAssignableFrom(String.class);
    }

    public static boolean isPropertyTextType(String className, String propertyName) {
        return isPropertyTextType(getClassForName(className), propertyName);
    }

    public static boolean isPropertyNumericType(Class<?> clazz, String propertyName) {
        Class<?> propertyType = getPropertyTypeByClassAndPropertyName(clazz, propertyName);
        return propertyType.isAssignableFrom(Integer.class) || propertyType.isAssignableFrom(Double.class);
    }

    public static boolean isPropertyNumericType(String className, String propertyName) {
        return isPropertyNumericType(getClassForName(className), propertyName);
    }

    public static boolean isPropertyDateTimeType(Class<?> clazz, String propertyName) {
        Class<?> propertyType = getPropertyTypeByClassAndPropertyName(clazz, propertyName);
        return propertyType.isAssignableFrom(LocalDate.class) || propertyType.isAssignableFrom(LocalDateTime.class);
    }

    public static boolean isPropertyDateTimeType(String className, String propertyName) {
        return isPropertyDateTimeType(getClassForName(className), propertyName);
    }

    public static boolean isPropertyBooleanType(Class<?> clazz, String propertyName) {
        Class<?> propertyType = getPropertyTypeByClassAndPropertyName(clazz, propertyName);
        return propertyType.isAssignableFrom(Boolean.class);
    }

    public static boolean isPropertyBooleanType(String className, String propertyName) {
        return isPropertyBooleanType(getClassForName(className), propertyName);
    }

    private static Class<?> getPropertyTypeByClassAndPropertyName(Class<?> clazz, String propertyName) {
        try {
            int count = org.apache.commons.lang3.StringUtils.countMatches(propertyName, ".");
            String[] parts = propertyName.split("\\.");

            switch (count) {
                case 0:
                    return clazz.getDeclaredField(parts[0]).getType();
                case 1:
                    return clazz.getDeclaredField(parts[0]).getType().getDeclaredField(parts[1]).getType();
                case 2:
                    return clazz.getDeclaredField(parts[0]).getType().getDeclaredField(parts[1]).getType().getDeclaredField(parts[2]).getType();
                default:
                    throw new IllegalArgumentException("Cannot search more than 2 nested objects.");
            }
        } catch (NoSuchFieldException | SecurityException ex) {
            throw new RuntimeException(ex);
        }
    }

    private static Class<?> getClassForName(String className) {
        try {
            return Class.forName(className);
        } catch (ClassNotFoundException ex) {
            throw new RuntimeException(ex);
        }
    }
}
