package com.stefano.draiver.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum MoveStatus {
    REQUESTED,
    ASSIGNED,
    DELIVERED,
    CANCELED
}
