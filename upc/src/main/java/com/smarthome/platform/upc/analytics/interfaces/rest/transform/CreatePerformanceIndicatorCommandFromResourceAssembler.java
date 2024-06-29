package com.smarthome.platform.upc.analytics.interfaces.rest.transform;

import com.smarthome.platform.upc.analytics.domain.model.commands.CreatePerformanceIndicatorCommand;
import com.smarthome.platform.upc.analytics.interfaces.rest.resources.CreatePerformanceIndicatorResource;

public class CreatePerformanceIndicatorCommandFromResourceAssembler {
    public static CreatePerformanceIndicatorCommand toCommandFromResource(CreatePerformanceIndicatorResource resource) {
        return new CreatePerformanceIndicatorCommand(
                resource.name(),
                resource.description(),
                resource.minValue(),
                resource.maxValue(),
                resource.deviceType());
    }
}
