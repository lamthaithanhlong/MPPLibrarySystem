package org.miu.mpp.utils;

public enum AppStringConstants {
    PASSWORD_INCORRECT("Password incorrect"),

    WELCOME_TEXT("Welcome to STARBOYs LMS"),

    PASSWORD("Password"),

    ID("id"),

    ERROR("Error"),

    ID_PASSWORD_CANNOT_BE_EMPTY("id or password cannot be empty"),

    LOGIN("Login"),

    MEMBER_CANNOT_BE_NULL("Library member cannot be null");

    String value;

    AppStringConstants(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
