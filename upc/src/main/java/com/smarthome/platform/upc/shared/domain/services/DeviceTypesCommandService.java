package com.smarthome.platform.upc.shared.domain.services;

import com.smarthome.platform.upc.shared.domain.model.commands.PopulateDeviceTypesCommand;

public interface DeviceTypesCommandService {
    void handle(PopulateDeviceTypesCommand populateDeviceTypesCommand);
}
