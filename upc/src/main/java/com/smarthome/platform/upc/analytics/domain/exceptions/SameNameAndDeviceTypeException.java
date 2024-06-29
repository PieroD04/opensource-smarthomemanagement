package com.smarthome.platform.upc.analytics.domain.exceptions;

public class SameNameAndDeviceTypeException extends RuntimeException{
    public SameNameAndDeviceTypeException(String name, String deviceType) {
        super("Performance Indicator with name " + name + " and deviceType " + deviceType + " already exists");
    }
}
