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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
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
public class Driver {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    @Enumerated(EnumType.STRING)
    private License license;
    @Enumerated(EnumType.STRING)
    private DriverRate rate;
    @Enumerated(EnumType.STRING)
    private DriverStatus status;
    @Embedded
    private Location location;
    @Version
    private Integer version;
    @OneToMany(mappedBy = "driver", fetch = FetchType.LAZY)
    private List<Move> moves;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime deletedAt;
}
