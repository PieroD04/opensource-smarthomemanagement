package com.smarthome.platform.upc.inventory.application.internal.commandservices;

import com.smarthome.platform.upc.analytics.infrastructure.persistence.jpa.repositories.PerformanceIndicatorRepository;
import com.smarthome.platform.upc.inventory.domain.exceptions.PerformanceIndicatorNotFoundException;
import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.services.DeviceCommandService;
import com.smarthome.platform.upc.inventory.infrastructure.persistence.jpa.repositories.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeviceCommandServiceImpl implements DeviceCommandService {
    private final DeviceRepository deviceRepository;
    private final PerformanceIndicatorRepository performanceIndicatorRepository;

    public DeviceCommandServiceImpl(DeviceRepository deviceRepository, PerformanceIndicatorRepository performanceIndicatorRepository) {
        this.deviceRepository = deviceRepository;
        this.performanceIndicatorRepository = performanceIndicatorRepository;
    }

    @Override
    public Long handle(CreateDeviceCommand command) {
        var performanceIndicator = performanceIndicatorRepository.findById(command.performanceIndicatorId());
        if (performanceIndicator.isEmpty()) { throw new PerformanceIndicatorNotFoundException(command.performanceIndicatorId()); }

        Device device = new Device(command, performanceIndicator.get());
        try {
            deviceRepository.save(device);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while saving device: " + e.getMessage());
        }
        return device.getId();
    }

    @Override
    public Optional<Device> handle(UpdateDeviceCommand command) {
        var device = deviceRepository.findById(command.id());
        if (device.isEmpty()) { return Optional.empty(); }
        Device deviceToUpdate = device.get();

        var performanceIndicator = performanceIndicatorRepository.findById(command.performanceIndicatorId());
        if (performanceIndicator.isEmpty()) { throw new PerformanceIndicatorNotFoundException(command.performanceIndicatorId()); }

        try {
            Device updatedDevice = deviceRepository.save(deviceToUpdate.update(command, performanceIndicator.get()));
            return Optional.of(updatedDevice);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating device: " + e.getMessage());
        }
    }
}
