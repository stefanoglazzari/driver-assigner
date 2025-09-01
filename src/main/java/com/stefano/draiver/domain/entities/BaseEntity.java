package com.stefano.draiver.domain.entities;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;

    @Version
    private Integer version;

    private LocalDateTime createdAt, updatedAt, deletedAt;

    public void beforeUpdate() {}

    public void validateOnSave() {}

    public void validateOnDelete() {}

    public void inactivate() {
        deletedAt = LocalDateTime.now();
    }

    public boolean isActive() {
        return Objects.isNull(deletedAt);
    }

}
