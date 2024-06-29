package com.smarthome.platform.upc.inventory.application.internal.commandservices;

import com.smarthome.platform.upc.inventory.domain.exceptions.DeviceNotFoundException;
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

    public DeviceCommandServiceImpl(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    @Override
    public Long handle(CreateDeviceCommand command) {
        Device device = new Device(command);
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
        if (device.isEmpty()) { throw new DeviceNotFoundException(command.id()); }
        Device deviceToUpdate = device.get();
        try {
            Device updatedDevice = deviceRepository.save(deviceToUpdate.update(command));
            return Optional.of(updatedDevice);
        } catch (Exception e) {
            throw new IllegalArgumentException("Error while updating device: " + e.getMessage());
        }
    }
}
