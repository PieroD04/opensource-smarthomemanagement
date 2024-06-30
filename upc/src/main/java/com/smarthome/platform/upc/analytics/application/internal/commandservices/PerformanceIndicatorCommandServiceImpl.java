package com.smarthome.platform.upc.analytics.application.internal.commandservices;

import com.smarthome.platform.upc.analytics.domain.exceptions.InvalidRangeException;
import com.smarthome.platform.upc.analytics.domain.exceptions.SameNameAndDeviceTypeException;
import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.analytics.domain.model.commands.CreatePerformanceIndicatorCommand;
import com.smarthome.platform.upc.analytics.domain.services.PerformanceIndicatorCommandService;
import com.smarthome.platform.upc.analytics.infrastructure.persistence.jpa.repositories.PerformanceIndicatorRepository;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import org.springframework.stereotype.Service;

@Service
public class PerformanceIndicatorCommandServiceImpl implements PerformanceIndicatorCommandService {
    private final PerformanceIndicatorRepository performanceIndicatorRepository;

    public PerformanceIndicatorCommandServiceImpl(PerformanceIndicatorRepository performanceIndicatorRepository) {
        this.performanceIndicatorRepository = performanceIndicatorRepository;
    }

    @Override
    public Long handle(CreatePerformanceIndicatorCommand command) {
        if (performanceIndicatorRepository.existsByNameAndDeviceType(
                command.name(),
                DeviceType.valueOf(command.deviceType()))) {
            throw new SameNameAndDeviceTypeException(command.name(), command.deviceType());
        }

        if (command.maxValue() <= command.minValue()) {
            throw new InvalidRangeException(command.minValue(), command.maxValue());
        }

        var performanceIndicator = new PerformanceIndicator(command);
        try {
            performanceIndicatorRepository.save(performanceIndicator);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving performance indicator: " + e.getMessage());
        }
        return performanceIndicator.getId();
    }
}
