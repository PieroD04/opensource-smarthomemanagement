package com.smarthome.platform.upc.inventory.domain.model.aggregates;

import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.valueobjects.Status;
import com.smarthome.platform.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.Date;

@Entity
@Getter
public class Device extends AuditableAbstractAggregateRoot<Device> {
    @NotNull(message = "Serial number is required")
    @Max(value = 30, message = "Serial number must be less than or equal to 30 characters")
    @Column(unique = true)
    private String serialNumber;

    @NotNull(message = "Model is required")
    @Max(value = 50, message = "Model must be less than or equal to 50 characters")
    private String model;

    @NotNull(message = "Device type is required")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @NotNull(message = "Installation date is required")
    @FutureOrPresent(message = "Installation date must not be in the past")
    private Date installationDate;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Device() {}

    public Device(CreateDeviceCommand command){
        this.serialNumber = command.serialNumber();
        this.model = command.model();
        this.deviceType = DeviceType.valueOf(command.deviceType());
        this.installationDate = command.installationDate();
        this.status = Status.valueOf(command.status());
    }

}
