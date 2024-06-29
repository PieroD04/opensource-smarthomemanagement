package com.smarthome.platform.upc.analytics.interfaces.rest.resources;

public record CreatePerformanceIndicatorResource(String name, String description, Double minValue, Double maxValue, String deviceType) {
}
