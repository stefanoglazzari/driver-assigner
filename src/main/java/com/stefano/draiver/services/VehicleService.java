package com.stefano.draiver.services;

import aj.org.objectweb.asm.commons.Remapper;
import com.stefano.draiver.domain.entities.BaseEntity;
import com.stefano.draiver.domain.entities.Owner;
import com.stefano.draiver.domain.entities.Vehicle;
import com.stefano.draiver.dtos.vehicle.PatchRequestVehicle;
import com.stefano.draiver.exceptions.ResourceNotFoundException;
import com.stefano.draiver.mappers.VehicleMapper;
import com.stefano.draiver.repositories.interfaces.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class VehicleService extends BaseService<Vehicle>{

    private VehicleMapper vehicleMapper;

    private OwnerService ownerService;

    @Getter
    private VehicleRepository repository;

    @Transactional(readOnly = true)
    public Page<Vehicle> list(Pageable pageable) {
        return repository.findByDeletedAtIsNull(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Vehicle> listByOwner(Pageable pageable, UUID ownerId) {
        return repository.findByDeletedAtIsNullAndOwnerId(pageable, ownerId);
    }

    @Transactional
    public Vehicle create(Vehicle vehicle, UUID ownerId) {
        Owner owner = ownerService.get(ownerId);
        vehicle.setOwner(owner);
        return create(vehicle);
    }

    @Transactional(readOnly = true)
    public Vehicle get(UUID id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Vehicle update(UUID id, PatchRequestVehicle patchRequestVehicle) {
        Vehicle vehicle = get(id);
        vehicleMapper.patch(vehicle, patchRequestVehicle);

        if (!Objects.isNull(patchRequestVehicle.ownerId())) {
            Owner owner = ownerService.get(patchRequestVehicle.ownerId());
            vehicle.setOwner(owner);
        }

        update(vehicle);
        return vehicle;
    }

    @Override
    public void beforeCreate(Vehicle vehicle) {
        vehicle.setCreatedAt(LocalDateTime.now());
        vehicle.setUpdatedAt(vehicle.getCreatedAt());
        vehicle.setId(null);
    }

    @Override
    public void beforeUpdate(Vehicle vehicle) {
        vehicle.setUpdatedAt(LocalDateTime.now());
    }
}
