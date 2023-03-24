package org.miu.mpp.utils;

public enum AppConstants {
    LATE_FEE(0.25f),

    PASSWORD_INCORRECT("Password incorrect");

    float lateFee;

    String value;

    AppConstants(float lateFee) {
        this.lateFee = lateFee;
    }

    AppConstants(String value) {
        this.value = value;
    }

    public float getLateFee() {
        return lateFee;
    }

    public String getValue() {
        return value;
    }
}
