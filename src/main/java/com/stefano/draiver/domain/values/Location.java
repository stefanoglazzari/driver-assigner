package com.stefano.draiver.domain.values;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Location {

    private BigDecimal lat;
    private BigDecimal lon;
}
