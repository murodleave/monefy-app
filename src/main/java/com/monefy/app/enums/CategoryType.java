package com.monefy.app.enums;

public enum CategoryType {
    INCOME,
    EXPENSE;

    public static CategoryType fromString(String type) {
        return CategoryType.valueOf(type.toUpperCase());
    }
}
