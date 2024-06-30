package com.smarthome.platform.upc.analytics.domain.model.aggregates;


import com.smarthome.platform.upc.analytics.domain.model.commands.CreatePerformanceIndicatorCommand;
import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import com.smarthome.platform.upc.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
public class PerformanceIndicator extends AuditableAbstractAggregateRoot<PerformanceIndicator> {
    @NotNull(message = "Name is required")
    @Size(max = 40, message = "Name must be less than or equal to 40 characters")
    @Getter
    private String name;

    // Description is optional
    @Size(max = 200, message = "Description must be less than or equal to 200 characters")
    @Getter
    private String description;

    @NotNull(message = "Min value is required")
    @Getter
    private Double minValue;

    @NotNull(message = "Max value is required")
    @Getter
    private Double maxValue;

    @NotNull(message = "Device type is required")
    @Enumerated(EnumType.STRING)
    private DeviceType deviceType;

    @OneToMany(mappedBy = "performanceIndicator")
    @Getter
    private List<Device> devices;

    public PerformanceIndicator() {}

    public PerformanceIndicator(CreatePerformanceIndicatorCommand command)
    {
        this.name = command.name();
        this.description = command.description();
        this.minValue = command.minValue();
        this.maxValue = command.maxValue();
        this.deviceType = DeviceType.valueOf(command.deviceType());
    }

    public String getDeviceType() {
        return deviceType.name();
    }


}
