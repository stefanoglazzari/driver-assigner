package com.stefano.draiver.controller;

import com.stefano.draiver.domain.entities.Driver;
import com.stefano.draiver.domain.enums.DriverStatus;
import com.stefano.draiver.dtos.driver.CreateRequestDriver;
import com.stefano.draiver.dtos.driver.DriverResponse;
import com.stefano.draiver.dtos.driver.PatchRequestDriver;
import com.stefano.draiver.mappers.DriverMapper;
import com.stefano.draiver.services.DriverService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/api/drivers")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService service;
    private final DriverMapper mapper;

    @GetMapping
    public Page<DriverResponse> list(
            @RequestParam(value = "status", required = false) DriverStatus status,
            Pageable pageable
    ) {
        return service.list(status, pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponse> get(@PathVariable UUID id) {
        return  ResponseEntity
                .ok()
                .body(  mapper.toResponse(service.get(id)));
    }

    @PostMapping
    public ResponseEntity<DriverResponse> create(@Valid @RequestBody CreateRequestDriver req) {
        Driver created = service.create(mapper.toEntity(req));
        return ResponseEntity
                .created(URI.create("/api/drivers/" + created.getId()))
                .body(mapper.toResponse(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DriverResponse> patch(@PathVariable UUID id, @Valid @RequestBody PatchRequestDriver req) {
        Driver updated = service.update(id, req);
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
