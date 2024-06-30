package com.smarthome.platform.upc.inventory.interfaces.rest.resources;

import java.util.Date;

public record CreateDeviceResource(String serialNumber, String model, String deviceType, Date installationDate, String status, Long performanceIndicatorId) {
}
