package com.stefano.draiver.domain.entities;

import com.stefano.draiver.domain.enums.MoveStatus;
import com.stefano.draiver.domain.values.Location;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Move extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Owner owner;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    private Driver driver;

    @Enumerated(EnumType.STRING)
    private MoveStatus status;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="lat", column=@Column(name="from_lat", precision=10, scale=7)),
            @AttributeOverride(name="lon", column=@Column(name="from_lon", precision=10, scale=7))
    })
    private Location from;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="lat", column=@Column(name="to_lat", precision=10, scale=7)),
            @AttributeOverride(name="lon", column=@Column(name="to_lon", precision=10, scale=7))
    })
    private Location to;
    private BigDecimal eta;
}
