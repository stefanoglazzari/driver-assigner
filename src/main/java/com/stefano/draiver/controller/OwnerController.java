package com.stefano.draiver.controller;

import com.stefano.draiver.domain.entities.Owner;
import com.stefano.draiver.dtos.owner.CreateRequestOwner;
import com.stefano.draiver.dtos.owner.OwnerResponse;
import com.stefano.draiver.dtos.owner.PatchRequestOwner;
import com.stefano.draiver.mappers.OwnerMapper;
import com.stefano.draiver.services.OwnerService;
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
@RequestMapping("/api/owners")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService service;
    private final OwnerMapper mapper;

    @GetMapping
    public Page<OwnerResponse> list(
            Pageable pageable
    ) {
        return service.list(pageable).map(mapper::toResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerResponse> get(@PathVariable UUID id) {
        return  ResponseEntity
                .ok()
                .body(  mapper.toResponse(service.get(id)));
    }

    @PostMapping
    public ResponseEntity<OwnerResponse> create(@Valid @RequestBody CreateRequestOwner req) {
        Owner created = service.create(mapper.toEntity(req));
        return ResponseEntity
                .created(URI.create("/api/drivers/" + created.getId()))
                .body(mapper.toResponse(created));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OwnerResponse> patch(@PathVariable UUID id, @Valid @RequestBody PatchRequestOwner req) {
        Owner updated = service.update(id, req);
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
