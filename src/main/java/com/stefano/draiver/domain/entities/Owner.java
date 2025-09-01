package com.stefano.draiver.domain.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
public class Owner extends BaseEntity {

    private String name;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Vehicle> vehicles;
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Move> moves;
}
