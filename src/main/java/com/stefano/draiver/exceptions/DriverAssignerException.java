package com.stefano.draiver.exceptions;

public abstract class DriverAssignerException extends RuntimeException {

    protected DriverAssignerException(String message) {
        super(message);
    }

    protected DriverAssignerException(String message, Throwable cause) {
        super(message, cause);
    }
}
