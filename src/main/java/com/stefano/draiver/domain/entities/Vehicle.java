package com.stefano.draiver.domain.entities;

import com.stefano.draiver.domain.enums.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Version;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;
    private String description;
    private String vin;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    @Version
    private Integer version;
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<Move> moves;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
