package com.smarthome.platform.upc.inventory.domain.services;

import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;

import java.util.Optional;

public interface DeviceCommandService {
    Long handle(CreateDeviceCommand createDeviceCommand);
    Optional<Device> handle(UpdateDeviceCommand updateDeviceCommand);
}
