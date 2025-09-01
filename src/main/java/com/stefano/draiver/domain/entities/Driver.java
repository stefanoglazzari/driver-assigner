package com.stefano.draiver.domain.entities;

import com.stefano.draiver.domain.enums.DriverRate;
import com.stefano.draiver.domain.enums.DriverStatus;
import com.stefano.draiver.domain.enums.License;
import com.stefano.draiver.domain.values.Location;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Driver extends BaseEntity {

    private String name;
    @Enumerated(EnumType.STRING)
    private License license;
    @Enumerated(EnumType.STRING)
    private DriverRate rate;
    @Enumerated(EnumType.STRING)
    private DriverStatus status;
    @Embedded
    private Location location;
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Move> moves;
}
