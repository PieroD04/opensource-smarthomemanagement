package com.smarthome.platform.upc.inventory.interfaces.rest.transform;

import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.UpdateDeviceResource;

public class UpdateDeviceCommandFromResourceAssembler {
    public static UpdateDeviceCommand toCommandFromResource(Long deviceId, UpdateDeviceResource resource) {
        return new UpdateDeviceCommand(
                deviceId,
                resource.serialNumber(),
                resource.model(),
                resource.deviceType(),
                resource.installationDate(),
                resource.status(),
                resource.performanceIndicatorId()
        );
    }
}
