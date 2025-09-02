package com.stefano.draiver.services;

import com.stefano.draiver.domain.entities.BaseEntity;
import com.stefano.draiver.domain.entities.Driver;
import com.stefano.draiver.domain.enums.DriverStatus;
import com.stefano.draiver.dtos.driver.PatchRequestDriver;
import com.stefano.draiver.exceptions.ResourceNotFoundException;
import com.stefano.draiver.mappers.DriverMapper;
import com.stefano.draiver.repositories.interfaces.DriverRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class DriverService extends BaseService<Driver>{

    private DriverMapper driverMapper;

    @Getter
    private DriverRepository repository;

    @Transactional(readOnly = true)
    public Page<Driver> list(DriverStatus status, Pageable pageable) {
        return (status == null)
                ? repository.findByDeletedAtIsNull(pageable)
                : repository.findByDeletedAtIsNullAndStatus(status, pageable);
    }

    @Transactional(readOnly = true)
    public Driver get(UUID id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Driver update(UUID id, PatchRequestDriver patchDriverRequest) {
        Driver driver = get(id);
        driverMapper.patch(driver, patchDriverRequest);
        update(driver);
        return driver;
    }

    @Override
    public void beforeCreate(Driver driver) {
        driver.setCreatedAt(LocalDateTime.now());
        driver.setUpdatedAt(driver.getCreatedAt());
        driver.setId(null);
    }

    @Override
    public void beforeUpdate(Driver driver) {
        driver.setUpdatedAt(LocalDateTime.now());
    }
}
