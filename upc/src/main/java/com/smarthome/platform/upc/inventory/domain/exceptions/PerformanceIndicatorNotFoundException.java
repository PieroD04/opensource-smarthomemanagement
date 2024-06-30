package com.smarthome.platform.upc.inventory.domain.exceptions;

public class PerformanceIndicatorNotFoundException extends RuntimeException{
    public PerformanceIndicatorNotFoundException(Long performanceIndicatorId) {
        super("Performance indicator with id " + performanceIndicatorId + " not found");
    }
}
