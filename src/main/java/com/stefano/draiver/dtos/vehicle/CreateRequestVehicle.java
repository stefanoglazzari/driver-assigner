package com.stefano.draiver.dtos.vehicle;

import com.stefano.draiver.domain.enums.VehicleType;

import java.util.UUID;

public record CreateRequestVehicle(
        String description,
        String vin,
        VehicleType vehicleType,
        UUID ownerId
) {}