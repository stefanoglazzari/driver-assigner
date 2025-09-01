package com.stefano.draiver.domain.entities;

import com.stefano.draiver.domain.enums.VehicleType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;
    private String description;
    private String vin;
    @Enumerated(EnumType.STRING)
    private VehicleType type;
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<Move> moves;
}
