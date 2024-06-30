package com.smarthome.platform.upc.inventory.domain.model.aggregates;

import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.inventory.domain.model.commands.CreateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.commands.UpdateDeviceCommand;
import com.smarthome.platform.upc.inventory.domain.model.valueobjects.Status;
import com.smarthome.platform.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.Date;

@Entity
public class Device extends AuditableAbstractAggregateRoot<Device> {
    @NotNull(message = "Serial number is required")
    @Size(max = 30, message = "Serial number must be less than or equal to 30 characters")
    @Column(unique = true)
    @Getter
    private String serialNumber;

    @NotNull(message = "Model is required")
    @Size(max = 50, message = "Model must be less than or equal to 50 characters")
    @Getter
    private String model;

    @NotNull(message = "Device type is required")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @NotNull(message = "Installation date is required")
    @FutureOrPresent(message = "Installation date must not be in the past")
    @Getter
    private Date installationDate;

    @NotNull(message = "Status is required")
    @Embedded
    private Status status;

    @ManyToOne
    @JoinColumn(name = "performance_indicator_id", nullable = false)
    @Getter
    private PerformanceIndicator performanceIndicator;

    public Device() {}

    public Device(CreateDeviceCommand command, PerformanceIndicator performanceIndicator) {
        this.serialNumber = command.serialNumber();
        this.model = command.model();
        this.deviceType = DeviceType.valueOf(command.deviceType());
        this.installationDate = command.installationDate();
        this.status = new Status(command.status());
        this.performanceIndicator = performanceIndicator;
    }

    public Device update(UpdateDeviceCommand command, PerformanceIndicator performanceIndicator) {
        this.serialNumber = command.serialNumber();
        this.model = command.model();
        this.deviceType = DeviceType.valueOf(command.deviceType());
        this.installationDate = command.installationDate();
        this.status = new Status(command.status());
        this.performanceIndicator = performanceIndicator;

        return this;
    }

    public String getDeviceType() {
        return deviceType.name();
    }

    public String getStatus() {
        return status.status();
    }

}
