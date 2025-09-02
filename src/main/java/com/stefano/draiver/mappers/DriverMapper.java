package com.stefano.draiver.mappers;

import com.stefano.draiver.domain.entities.Driver;
import com.stefano.draiver.domain.values.Location;
import com.stefano.draiver.dtos.driver.CreateRequestDriver;
import com.stefano.draiver.dtos.driver.DriverResponse;
import com.stefano.draiver.dtos.driver.PatchRequestDriver;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DriverMapper {

    public void patch(Driver driver, PatchRequestDriver request ) {
        if (request == null) return;

        if (request.name() != null)  {
            driver.setName(request.name());
        }
        if (request.license() != null) {
            driver.setLicense(request.license());
        }
        if (request.rate() != null) {
            driver.setRate(request.rate());
        }
        if (request.status() != null) {
            driver.setStatus(request.status());
        }

        if (request.lat() != null || request.lon() != null) {
            BigDecimal newLat = request.lat() != null ? request.lat()
                    : (driver.getLocation() != null ? driver.getLocation().getLat() : null);
            BigDecimal newLon = request.lon() != null ? request.lon()
                    : (driver.getLocation() != null ? driver.getLocation().getLon() : null);

            driver.setLocation(new Location(newLat, newLon));
        }
    }

    public DriverResponse toResponse(Driver driver) {
        if (driver == null) return null;
        return new DriverResponse(
                driver.getId(),
                driver.getName(),
                driver.getLicense(),
                driver.getRate(),
                driver.getStatus(),
                driver.getLocation()
        );
    }

    public Driver toEntity(CreateRequestDriver req) {
        if (req == null) return null;

        Driver driver = Driver.builder()
                .name(req.name())
                .license(req.license())
                .status(req.status())
                .build();

        if (req.lat() != null || req.lon() != null) {
            driver.setLocation(new Location(req.lat(), req.lon()));
        }

        return driver;
    }
}
