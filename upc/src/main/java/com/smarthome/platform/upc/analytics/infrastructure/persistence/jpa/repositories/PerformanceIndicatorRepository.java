package com.smarthome.platform.upc.analytics.infrastructure.persistence.jpa.repositories;

import com.smarthome.platform.upc.analytics.domain.model.aggregates.PerformanceIndicator;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PerformanceIndicatorRepository extends JpaRepository<PerformanceIndicator, Long> {
    boolean existsByNameAndDeviceType(String name, DeviceType deviceType);
}
