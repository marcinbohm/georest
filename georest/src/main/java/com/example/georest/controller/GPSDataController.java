package com.example.georest.controller;

import com.example.georest.model.GPSData;
import com.example.georest.service.GPSDataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/gpsdata")
@AllArgsConstructor
public class GPSDataController {

    private final GPSDataService gpsDataService;

    @GetMapping
    public List<GPSData> getAllGPSData() {
        return gpsDataService.getAllGPSData();
    }

    @GetMapping(path = "/bydevice/{deviceId}")
    public List<GPSData> getGPSDataByDeviceId(
            @PathVariable("deviceId") Long deviceId) {
        return gpsDataService.getGPSDataByDeviceId(deviceId);
    }

    @PostMapping
    public void addGPSData(@Valid @RequestBody GPSData gpsData) {
        gpsDataService.addGPSData(gpsData);
    }

    @PutMapping
    public void updateGPSData(@Valid @RequestBody GPSData gpsData) {
        gpsDataService.updateGPSData(gpsData);
    }

    @DeleteMapping(path = "{gpsDataId}")
    public void deleteGPSData(
            @PathVariable("gpsDataId") Long gpsDataId) {
        gpsDataService.deleteGPSData(gpsDataId);
    }
}
