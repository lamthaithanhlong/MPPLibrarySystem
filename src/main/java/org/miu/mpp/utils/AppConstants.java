package org.miu.mpp.utils;

public enum AppConstants {
    LATE_FEE(0.25f),
    NEW_B00K_DEFAULT_COPY(1);

    float lateFee;

    int oneBookCopy;

    AppConstants(float lateFee) {
        this.lateFee = lateFee;
    }

    AppConstants(int oneBookCopy) {
        this.oneBookCopy = oneBookCopy;
    }

    public float getLateFee() {
        return lateFee;
    }

    public int getOneBookCopy() {
        return oneBookCopy;
    }
}
