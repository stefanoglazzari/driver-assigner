package com.stefano.draiver.domain.values;

import jakarta.persistence.Embeddable;

import java.math.BigDecimal;

@Embeddable
public class Location {

    private BigDecimal lat;
    private BigDecimal lon;
}
