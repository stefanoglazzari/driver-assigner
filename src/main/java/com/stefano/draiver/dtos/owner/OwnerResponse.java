package com.stefano.draiver.dtos.owner;

import java.util.UUID;

public record OwnerResponse(
        UUID id,
        String name
) {}