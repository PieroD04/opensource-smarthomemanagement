package com.smarthome.platform.upc.inventory.interfaces.rest.transform;

import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.DeviceResource;

public class DeviceResourceFromEntityAssembler {
    public static DeviceResource toResourceFromEntity(Device entity) {
        return new DeviceResource(
                entity.getId(),
                entity.getSerialNumber(),
                entity.getModel(),
                entity.getDeviceType(),
                entity.getInstallationDate(),
                entity.getStatus(),
                entity.getPerformanceIndicator().getId()
        );
    }
}
