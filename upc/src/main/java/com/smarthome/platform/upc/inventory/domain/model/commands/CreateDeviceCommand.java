package com.smarthome.platform.upc.inventory.domain.model.commands;

import java.util.Date;

public record CreateDeviceCommand(String serialNumber, String model, String deviceType,
                                  Date installationDate, String status, Long performanceIndicatorId){
}
