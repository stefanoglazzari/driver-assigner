package com.stefano.draiver.repositories.interfaces;

import com.stefano.draiver.domain.entities.Vehicle;
import com.stefano.draiver.repositories.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.UUID;


@Repository
public interface VehicleRepository extends BaseRepository<Vehicle> {

    Page<Vehicle> findByDeletedAtIsNull(Pageable pageable);

    Page<Vehicle> findByDeletedAtIsNullAndOwnerId(Pageable pageable, UUID ownerId);
}
