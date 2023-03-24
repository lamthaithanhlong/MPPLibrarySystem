package org.miu.mpp.utils;

public enum AppConstants {
    LATE_FEE(0.25f),
    PASSWORD_INCORRECT("Password incorrect"),

    MEMBER_CANNOT_BE_NULL("Library member cannot be null"),
    NEW_B00K_DEFAULT_COPY(1);

    float lateFee;

    int oneBookCopy;

    String value;

    AppConstants(float lateFee) {
        this.lateFee = lateFee;
    }

    AppConstants(String value) {
        this.value = value;
    }

    AppConstants(int oneBookCopy) {
        this.oneBookCopy = oneBookCopy;
    }

    public float getLateFee() {
        return lateFee;
    }

    public String getValue() {
        return value;
    }

    public int getOneBookCopy() {
        return oneBookCopy;
    }
}
