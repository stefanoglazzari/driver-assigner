package com.stefano.draiver.dtos.driver;

import com.stefano.draiver.domain.enums.DriverRate;
import com.stefano.draiver.domain.enums.DriverStatus;
import com.stefano.draiver.domain.enums.License;
import com.stefano.draiver.domain.values.Location;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;

import java.math.BigDecimal;
import java.util.UUID;

public record DriverResponse(
        UUID id,
        String name,
        License license,
        DriverRate rate,
        DriverStatus status,
        Location location
) {}