package com.dotoku.carhop.entity;

public enum ExpirationDuration {
    THIRTY_MIN(30),
    ONE_HOUR(60),
    ONE_HALF_HOUR(90),
    TWO_HOURS(120);

    private final int minutes;

    ExpirationDuration(int minutes) {
        this.minutes = minutes;
    }

    public int getMinutes() {
        return minutes;
    }
}

