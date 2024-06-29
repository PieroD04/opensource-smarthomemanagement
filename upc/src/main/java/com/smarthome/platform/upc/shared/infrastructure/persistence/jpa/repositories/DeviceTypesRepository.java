package com.smarthome.platform.upc.shared.infrastructure.persistence.jpa.repositories;

import com.smarthome.platform.upc.shared.domain.model.entities.DeviceTypes;
import com.smarthome.platform.upc.shared.domain.model.valueobjects.DeviceType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypesRepository extends JpaRepository<DeviceTypes, Long> {
    boolean existsByName(DeviceType name);
}
