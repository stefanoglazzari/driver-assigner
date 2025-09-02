package com.stefano.draiver.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public enum MoveStatus {
    REQUESTED,
    ASSIGNED,
    DELIVERED,
    CANCELED;

    public static List<MoveStatus> doNotAllowExclusion() {
        return List.of(REQUESTED, ASSIGNED);
    }
}
