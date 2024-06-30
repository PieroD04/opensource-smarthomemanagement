package com.smarthome.platform.upc.shared.application.internal.eventhandlers;

import com.smarthome.platform.upc.shared.domain.model.commands.PopulateDeviceTypesCommand;
import com.smarthome.platform.upc.shared.domain.services.DeviceTypesCommandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class ApplicationReadyEventHandler {
    private final DeviceTypesCommandService deviceTypesCommandService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationReadyEventHandler.class);

    public ApplicationReadyEventHandler(DeviceTypesCommandService deviceTypesCommandService) {
        this.deviceTypesCommandService = deviceTypesCommandService;
    }

    @EventListener
    public void on(ApplicationReadyEvent event) {
        var applicationName = event.getApplicationContext().getId();
        LOGGER.info("Starting to verify if the table device types population is needed for {} at {}", applicationName, currentTimestamp());
        var populateDeviceTypesCommand = new PopulateDeviceTypesCommand();
        deviceTypesCommandService.handle(populateDeviceTypesCommand);
        LOGGER.info("Table device types population verification finished for {} at {}", applicationName, currentTimestamp());
    }

    private Timestamp currentTimestamp() {
        return new Timestamp(System.currentTimeMillis());
    }
}
