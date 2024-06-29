package com.smarthome.platform.upc.shared.domain.model.entities;

import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import jakarta.persistence.*;

@Entity
public class DeviceTypes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private DeviceType name;

    public DeviceTypes() {}

    public DeviceTypes(DeviceType name) {
        this.name = name;
    }

    public String getName() {
        return this.name.name();
    }

    public static DeviceTypes toDeviceTypeFromName(String name) {
        return new DeviceTypes(DeviceType.valueOf(name));
    }
}
