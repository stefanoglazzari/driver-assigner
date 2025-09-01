package com.stefano.draiver.dtos.driver;

import com.stefano.draiver.domain.enums.DriverStatus;
import com.stefano.draiver.domain.enums.License;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;

public record CreateRequestDriver(
        String name,
        License license,
        DriverStatus status,
        @DecimalMin(value = "-90.0")  @DecimalMax(value = "90.0") BigDecimal lat,
        @DecimalMin(value = "-180.0") @DecimalMax(value = "180.0") BigDecimal lon
) {}