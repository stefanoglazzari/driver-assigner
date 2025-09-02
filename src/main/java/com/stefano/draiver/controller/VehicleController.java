package com.stefano.draiver.controller;

import com.stefano.draiver.domain.entities.Vehicle;
import com.stefano.draiver.dtos.vehicle.CreateRequestVehicle;
import com.stefano.draiver.dtos.vehicle.PatchRequestVehicle;
import com.stefano.draiver.dtos.vehicle.VehicleResponse;
import com.stefano.draiver.mappers.VehicleMapper;
import com.stefano.draiver.services.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/vehicles")
@RequiredArgsConstructor
public class VehicleController {

    private final VehicleService service;
    private final VehicleMapper mapper;

    @GetMapping
    public Page<VehicleResponse> list(
            Pageable pageable
    ) {
        return service.list(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponse> get(@PathVariable UUID id) {
        return  ResponseEntity
                .ok()
                .body(  mapper.toResponse(service.get(id)));
    }

    @PostMapping
    public ResponseEntity<VehicleResponse> create(@Valid @RequestBody CreateRequestVehicle req) {
        Vehicle created = service.create(mapper.toEntity(req), req.ownerId());
        return ResponseEntity
                .created(URI.create("/api/drivers/" + created.getId()))
                .body(mapper.toResponse(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<VehicleResponse> patch(@PathVariable UUID id, @Valid @RequestBody PatchRequestVehicle req) {
        Vehicle updated = service.update(id, req);
        return  ResponseEntity
                .ok()
                .body( mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
