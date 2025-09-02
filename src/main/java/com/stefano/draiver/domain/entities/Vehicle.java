package com.stefano.draiver.domain.entities;

import com.stefano.draiver.domain.enums.MoveStatus;
import com.stefano.draiver.domain.enums.VehicleType;
import com.stefano.draiver.exceptions.BusinessRuleException;
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
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@SuperBuilder
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
    @Builder.Default
    @OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY)
    private List<Move> moves = new ArrayList<>();
}
