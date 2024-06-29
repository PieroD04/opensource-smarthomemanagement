package com.smarthome.platform.upc.analytics.domain.services;

import com.smarthome.platform.upc.analytics.domain.model.commands.CreatePerformanceIndicatorCommand;

public interface PerformanceIndicatorCommandService {
    Long handle(CreatePerformanceIndicatorCommand command);
}
