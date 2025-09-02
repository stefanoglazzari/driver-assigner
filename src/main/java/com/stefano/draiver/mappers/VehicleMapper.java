package com.stefano.draiver.mappers;

import com.stefano.draiver.domain.entities.Owner;
import com.stefano.draiver.domain.entities.Vehicle;
import com.stefano.draiver.dtos.vehicle.CreateRequestVehicle;
import com.stefano.draiver.dtos.vehicle.PatchRequestVehicle;
import com.stefano.draiver.dtos.vehicle.VehicleResponse;
import org.springframework.stereotype.Component;

@Component
public class VehicleMapper {

    public VehicleResponse toResponse(Vehicle vehicle) {
        if (vehicle == null) return null;
        return new VehicleResponse(
                vehicle.getId(),
                vehicle.getDescription(),
                vehicle.getVin(),
                vehicle.getType(),
                vehicle.getOwner() != null ? vehicle.getOwner().getId() : null
        );
    }

    public Vehicle toEntity(CreateRequestVehicle req) {
        if (req == null) return null;

        Vehicle vehicle = new Vehicle();
        vehicle.setDescription(req.description());
        vehicle.setVin(req.vin());
        vehicle.setType(req.vehicleType());
        return vehicle;
    }

    public void patch(Vehicle vehicle, PatchRequestVehicle req) {
        if (req == null) return;

        if (req.description() != null) {
            vehicle.setDescription(req.description());
        }
        if (req.vin() != null) {
            vehicle.setVin(req.vin());
        }
        if (req.vehicleType() != null) {
            vehicle.setType(req.vehicleType());
        }
    }
}
