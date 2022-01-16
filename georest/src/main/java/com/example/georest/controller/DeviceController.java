package com.example.georest.controller;

import com.example.georest.model.Device;
import com.example.georest.service.DeviceService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/devices")
@AllArgsConstructor
public class DeviceController {

    private final DeviceService deviceService;

    @GetMapping
    public List<Device> getAllDevices() {
        return deviceService.getAllDevices();
    }

    @PostMapping
    public void addDevice(@Valid @RequestBody Device device) {
        deviceService.addDevice(device);
    }

    @PutMapping
    public void updateDevice(@Valid @RequestBody Device device) {
        deviceService.updateDevice(device);
    }

    @DeleteMapping(path = "{deviceId}")
    public void deleteDevice(
            @PathVariable("deviceId") Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }
}
