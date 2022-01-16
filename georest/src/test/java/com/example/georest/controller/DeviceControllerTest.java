package com.example.georest.controller;

import com.example.georest.model.Device;
import com.example.georest.service.DeviceService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.experimental.results.ResultMatchers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(DeviceController.class)
public class DeviceControllerTest {

    @MockBean
    DeviceService deviceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldSaveDevice() throws Exception {
        Device device = new Device(12345L, "test device", new ArrayList<>());

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/devices")
                .content(asJsonString(device))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk());
    }

    @Test
    public void shouldFetchInDb() throws Exception {
        List<Device> deviceList = Arrays.asList(
                new Device(1234L, "test device", new ArrayList<>()),
                new Device(123L, "test device2", new ArrayList<>()),
                new Device(12L, "test device2", new ArrayList<>())
        );

        Mockito.when(deviceService.getAllDevices()).thenReturn(deviceList);

        mockMvc.perform(
          MockMvcRequestBuilders.get("/api/v1/devices"))
                .andExpect(status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}