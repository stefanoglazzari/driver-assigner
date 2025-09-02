package com.stefano.draiver.exceptions;

public class BusinessRuleException extends DriverAssignerException {

    public BusinessRuleException(String message) {
        super(message);
    }

    public BusinessRuleException(String message, Throwable cause) {
        super(message, cause);
    }

    public static BusinessRuleException driverNotAvailable(String driverName) {
        return new BusinessRuleException("Driver " + driverName + " is not available for assignment");
    }

    public static BusinessRuleException invalidMoveStatus(String currentStatus, String targetStatus) {
        return new BusinessRuleException("Cannot change move status from " + currentStatus + " to " + targetStatus);
    }

    public static BusinessRuleException incompatibleLicense(String driverLicense, String requiredLicense) {
        return new BusinessRuleException("Driver license " + driverLicense + " is not compatible with required license " + requiredLicense);
    }
}
