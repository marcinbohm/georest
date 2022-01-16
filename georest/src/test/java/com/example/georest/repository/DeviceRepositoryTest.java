package com.example.georest.repository;

import com.example.georest.model.Device;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @BeforeEach
    void init() {
        List<Device> deviceList = Arrays.asList(
            new Device(123L, "test device", new ArrayList<>())
        );
        deviceRepository.saveAll(deviceList);
    }

    @AfterEach
    public void destroy() {
        deviceRepository.deleteAll();
    }

    @Test
    void shouldSaveAll() {
        List<Device> deviceList = Arrays.asList(
                new Device(123L, "test device", new ArrayList<>()),
                new Device(1232L, "test device2", new ArrayList<>()),
                new Device(1233L, "test device3", new ArrayList<>())
        );
        Iterable<Device> allDevices = deviceRepository.saveAll(deviceList);

        AtomicInteger validIdFound = new AtomicInteger();
        allDevices.forEach(device -> {
            if (device.getId()>0) {
                validIdFound.getAndIncrement();
            }
        });

        assertThat(validIdFound.intValue()).isEqualTo(3);
    }

    @Test
    void shouldFindAll() {
        List<Device> deviceList = deviceRepository.findAll();
        assertThat(deviceList.size()).isGreaterThanOrEqualTo(1);
    }
}