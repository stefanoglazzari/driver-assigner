package com.stefano.draiver.mappers;

import com.stefano.draiver.domain.entities.Driver;
import com.stefano.draiver.domain.entities.Owner;
import com.stefano.draiver.domain.values.Location;
import com.stefano.draiver.dtos.driver.CreateRequestDriver;
import com.stefano.draiver.dtos.driver.DriverResponse;
import com.stefano.draiver.dtos.driver.PatchRequestDriver;
import com.stefano.draiver.dtos.owner.CreateRequestOwner;
import com.stefano.draiver.dtos.owner.OwnerResponse;
import com.stefano.draiver.dtos.owner.PatchRequestOwner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class OwnerMapper {

    public void patch(Owner owner, PatchRequestOwner request ) {
        if (request == null) return;

        if (request.name() != null)  {
            owner.setName(request.name());
        }
    }

    public OwnerResponse toResponse(Owner owner) {
        if (owner == null) return null;
        return new OwnerResponse(
                owner.getId(),
                owner.getName()
        );
    }

    public Owner toEntity(CreateRequestOwner req) {
        if (req == null) return null;

        return Owner.builder()
                .name(req.name())
                .build();
    }
}
