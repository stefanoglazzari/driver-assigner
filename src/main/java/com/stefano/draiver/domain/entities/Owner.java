package com.stefano.draiver.domain.entities;


import com.stefano.draiver.domain.enums.MoveStatus;
import com.stefano.draiver.exceptions.BusinessRuleException;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Owner extends BaseEntity {

    private String name;
    @Builder.Default
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Move> moves = new ArrayList<>();

    @Override
    public void validateOnDelete() {
        moves.stream()
                .filter(move -> MoveStatus.doNotAllowExclusion().contains(move.getStatus()))
                .findAny()
                .ifPresent(m -> {
                    throw new BusinessRuleException("Owner has active moves");
                });
    }

    @Override
    public void inactivate() {
        super.inactivate();
        vehicles.forEach(BaseEntity::inactivate);
    }
}
