package com.stefano.draiver.repositories.interfaces;

import com.stefano.draiver.domain.entities.Owner;
import com.stefano.draiver.repositories.jpa.BaseRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;


@Repository
public interface OwnerRepository extends BaseRepository<Owner> {

    Page<Owner> findByDeletedAtIsNull(Pageable pageable);
}
