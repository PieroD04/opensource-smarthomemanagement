package com.smarthome.platform.upc.inventory.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record Status(String status) {
    public Status {
        if (!status.equals("ACTIVE") && !status.equals("INACTIVE")) {
            throw new IllegalArgumentException("Status should be either ACTIVE or INACTIVE");
        }
    }
}
