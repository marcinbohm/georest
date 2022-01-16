package com.example.georest.service;

import com.example.georest.model.Device;
import com.example.georest.repository.DeviceRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    DeviceService deviceService;

    @BeforeEach
    void init() {
        deviceService = new DeviceService(deviceRepository);
    }

    @Test
    public void shouldSaveDevice() {
        Device device = new Device();
        device.setId(123L);
        device.setName("test device");
        device.setGpsDataList(new ArrayList<>());

        when(deviceRepository.save(any(Device.class))).thenReturn(device);

        Device savedDevice = deviceRepository.save(device);
        assertThat(savedDevice.getName()).isNotNull();
    }

    @Test
    public void deviceExistsInDb() {
        Device device = new Device();
        device.setId(123L);
        device.setName("test device");
        device.setGpsDataList(new ArrayList<>());
        List<Device> deviceList = new ArrayList<>();
        deviceList.add(device);

        when(deviceRepository.findAll()).thenReturn(deviceList);

        List<Device> fetchedDevices = deviceService.getAllDevices();
        assertThat(fetchedDevices.size()).isGreaterThan(0);
    }
}