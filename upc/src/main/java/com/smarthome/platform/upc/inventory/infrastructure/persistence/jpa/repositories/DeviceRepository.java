package com.smarthome.platform.upc.inventory.infrastructure.persistence.jpa.repositories;


import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
}
