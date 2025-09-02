package com.stefano.draiver.domain.enums;

import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
public enum DriverRate {
    BAD,
    OK,
    GOOD,
    EXCELLENT;

    public static DriverRate getRate(BigDecimal rate) {
        if (rate == null) {
            throw new IllegalArgumentException("Rate cannot be null");
        }

        BigDecimal value = rate.setScale(1, RoundingMode.HALF_EVEN);

        if (value.compareTo(BigDecimal.valueOf(4)) < 0) {
            return BAD;
        } else if (value.compareTo(BigDecimal.valueOf(7)) < 0) {
            return OK;
        } else if (value.compareTo(BigDecimal.valueOf(9)) < 0) {
            return GOOD;
        } else {
            return EXCELLENT;
        }
    }
}
