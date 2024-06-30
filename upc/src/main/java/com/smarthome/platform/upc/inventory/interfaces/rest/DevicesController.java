package com.smarthome.platform.upc.inventory.interfaces.rest;

import com.smarthome.platform.upc.inventory.domain.model.aggregates.Device;
import com.smarthome.platform.upc.inventory.domain.model.queries.GetDeviceByIdQuery;
import com.smarthome.platform.upc.inventory.domain.services.DeviceCommandService;
import com.smarthome.platform.upc.inventory.domain.services.DeviceQueryService;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.CreateDeviceResource;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.DeviceResource;
import com.smarthome.platform.upc.inventory.interfaces.rest.resources.UpdateDeviceResource;
import com.smarthome.platform.upc.inventory.interfaces.rest.transform.CreateDeviceCommandFromResourceAssembler;
import com.smarthome.platform.upc.inventory.interfaces.rest.transform.DeviceResourceFromEntityAssembler;
import com.smarthome.platform.upc.inventory.interfaces.rest.transform.UpdateDeviceCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value="api/v1/devices", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Devices", description = "Device Management Endpoints")
public class DevicesController {
    private final DeviceCommandService deviceCommandService;
    private final DeviceQueryService deviceQueryService;

    public DevicesController(DeviceCommandService deviceCommandService, DeviceQueryService deviceQueryService) {
        this.deviceCommandService = deviceCommandService;
        this.deviceQueryService = deviceQueryService;
    }

    @PostMapping
    public ResponseEntity<?> createDevice(@RequestBody CreateDeviceResource createDeviceResource){
        var createDeviceCommand = CreateDeviceCommandFromResourceAssembler.toCommandFromResource(createDeviceResource);
        Long deviceId;
        try {
            deviceId = deviceCommandService.handle(createDeviceCommand);
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }

        if (deviceId == 0L){
            return ResponseEntity.badRequest().build();
        }
        var getDeviceByIdQuery = new GetDeviceByIdQuery(deviceId);
        var device = deviceQueryService.handle(getDeviceByIdQuery);
        if (device.isEmpty()){ return ResponseEntity.badRequest().build(); }
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(device.get());
        return new ResponseEntity<>(deviceResource, HttpStatus.CREATED);
    }

    @PutMapping("/{deviceId}")
    public ResponseEntity<?> updateDevice(@PathVariable Long deviceId, @RequestBody UpdateDeviceResource updateDeviceResource){
        var updateDeviceCommand = UpdateDeviceCommandFromResourceAssembler.toCommandFromResource(deviceId, updateDeviceResource);
        Optional<Device> updatedDevice;
        try {
            updatedDevice = deviceCommandService.handle(updateDeviceCommand);
        } catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(e.getMessage());
        }
        if (updatedDevice.isEmpty()){ return ResponseEntity.notFound().build(); }
        var deviceResource = DeviceResourceFromEntityAssembler.toResourceFromEntity(updatedDevice.get());
        return new ResponseEntity<>(deviceResource, HttpStatus.OK);
    }

}
