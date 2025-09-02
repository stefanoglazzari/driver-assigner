package com.stefano.draiver.services;

import com.stefano.draiver.domain.entities.BaseEntity;
import com.stefano.draiver.domain.entities.Owner;
import com.stefano.draiver.dtos.owner.PatchRequestOwner;
import com.stefano.draiver.exceptions.ResourceNotFoundException;
import com.stefano.draiver.mappers.OwnerMapper;
import com.stefano.draiver.repositories.interfaces.OwnerRepository;
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
public class OwnerService extends BaseService<Owner>{

    private OwnerMapper ownerMapper;

    @Getter
    private OwnerRepository repository;

    @Transactional(readOnly = true)
    public Page<Owner> list(Pageable pageable) {
        return repository.findByDeletedAtIsNull(pageable);
    }

    @Transactional(readOnly = true)
    public Owner get(UUID id) {
        return repository.findById(id)
                .filter(BaseEntity::isActive)
                .orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Owner update(UUID id, PatchRequestOwner patchOwnerRequest) {
        Owner owner = get(id);
        ownerMapper.patch(owner, patchOwnerRequest);
        update(owner);
        return owner;
    }

    @Override
    public void beforeCreate(Owner owner) {
        owner.setCreatedAt(LocalDateTime.now());
        owner.setUpdatedAt(owner.getCreatedAt());
        owner.setId(null);
    }

    @Override
    public void beforeUpdate(Owner owner) {
        owner.setUpdatedAt(LocalDateTime.now());
    }

}
