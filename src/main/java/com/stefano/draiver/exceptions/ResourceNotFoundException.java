package com.stefano.draiver.exceptions;

import java.util.UUID;

public class ResourceNotFoundException extends DriverAssignerException {

    public ResourceNotFoundException(UUID id) {
        super("Resource not found: " + id);
    }
}
