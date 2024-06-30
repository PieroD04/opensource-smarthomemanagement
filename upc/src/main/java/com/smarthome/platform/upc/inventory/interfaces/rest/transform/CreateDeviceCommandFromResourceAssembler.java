package com.smarthome.platform.upc.inventory.interfaces.rest.transform;

import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.CreateDeviceResource;

public class CreateDeviceCommandFromResourceAssembler {
    public static CreateDeviceCommand toCommandFromResource(CreateDeviceResource resource) {
        return new CreateDeviceCommand(
                resource.serialNumber(),
                resource.model(),
                resource.deviceType(),
                resource.installationDate(),
                resource.status(),
                resource.performanceIndicatorId()
        );
    }
}
