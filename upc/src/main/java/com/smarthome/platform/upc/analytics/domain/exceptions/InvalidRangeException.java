package com.smarthome.platform.upc.analytics.domain.exceptions;

public class InvalidRangeException extends RuntimeException{
    public InvalidRangeException(Double minValue, Double maxValue) {
        super("maxValue " + maxValue + " should be greater than minValue " + minValue);
    }
}
