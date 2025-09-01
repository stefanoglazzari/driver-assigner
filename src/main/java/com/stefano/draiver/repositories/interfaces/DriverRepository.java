package com.stefano.draiver.repositories.interfaces;

import com.stefano.draiver.domain.entities.Driver;
import com.stefano.draiver.domain.enums.DriverStatus;
import com.stefano.draiver.repositories.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends BaseRepository<Driver> {

    Page<Driver> findByDeletedAtIsNullAndStatus(DriverStatus status, Pageable pageable);
}
