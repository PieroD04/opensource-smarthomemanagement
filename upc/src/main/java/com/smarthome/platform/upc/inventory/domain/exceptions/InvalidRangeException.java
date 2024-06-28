package com.smarthome.platform.upc.inventory.domain.exceptions;

public class InvalidRangeException extends RuntimeException{
    public InvalidRangeException(Long minValue, Long maxValue) {
        super("maxValue " + maxValue + " should be greater than minValue " + minValue);
    }
}
