package com.smarthome.platform.upc.shared.application.internal.commandservices;

import com.smarthome.platform.upc.shared.domain.model.commands.PopulateDeviceTypesCommand;
import com.smarthome.platform.upc.shared.domain.model.entities.DeviceTypes;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import com.smarthome.platform.upc.shared.domain.services.DeviceTypesCommandService;
import com.smarthome.platform.upc.shared.infrastructure.persistence.jpa.repositories.DeviceTypesRepository;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DeviceTypesCommandServiceImpl implements DeviceTypesCommandService {
    private final DeviceTypesRepository deviceTypesRepository;

    public DeviceTypesCommandServiceImpl(DeviceTypesRepository deviceTypesRepository) {
        this.deviceTypesRepository = deviceTypesRepository;
    }

    @Override
    public void handle(PopulateDeviceTypesCommand populateDeviceTypesCommand) {
        Arrays.stream(DeviceType.values()).forEach(deviceType -> {
            if(!deviceTypesRepository.existsByName(deviceType))
                deviceTypesRepository.save(new DeviceTypes(deviceType));
        });
    }
}
