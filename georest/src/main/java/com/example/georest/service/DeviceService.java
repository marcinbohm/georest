package com.example.georest.service;

import com.example.georest.exceptions.BadRequestException;
import com.example.georest.exceptions.DeviceNotFoundException;
import com.example.georest.model.Device;
import com.example.georest.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public void addDevice(Device device) {
        if (deviceRepository.selectExistsDevice(device.getName())) {
            throw new BadRequestException(
                    "Device " + device.getName() + " already exists");
        }
        deviceRepository.save(device);
    }

    public void deleteDevice(Long deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException(
                    "Device with id " + deviceId + " does not exists");
        }
        deviceRepository.deleteById(deviceId);
    }

    public void updateDevice(Device device) {
        if (!deviceRepository.existsById(device.getId())) {
            throw new DeviceNotFoundException(
                    "Device with id " + device.getId() + " does not exists");
        }

        if (deviceRepository.selectExistsDevice(device.getName())) {
            throw new BadRequestException(
                    "Device " + device.getName() + " already exists");
        }

        deviceRepository.updateDevice(device.getId(),
                device.getName());
    }
}
