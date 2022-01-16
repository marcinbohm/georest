package com.example.georest.service;

import com.example.georest.exceptions.BadRequestException;
import com.example.georest.exceptions.GPSDataNotFoundException;
import com.example.georest.model.GPSData;
import com.example.georest.repository.GPSDataRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Service
public class GPSDataService {

    private final GPSDataRepository gpsDataRepository;

    public List<GPSData> getAllGPSData() {
        return gpsDataRepository.findAll();
    }

    public List<GPSData> getGPSDataByDeviceId(Long deviceId) {
        return gpsDataRepository.selectGPSDataListByDeviceId(deviceId);
    }

    public void addGPSData(GPSData gpsData) {
        if (!gpsDataRepository.selectExistsDevice(gpsData.getDeviceId())) {
            throw new BadRequestException(
                    "GPSData device not found with id " + gpsData.getDeviceId());
        }
        gpsDataRepository.save(gpsData);
    }

    public void deleteGPSData(Long gpsDataId) {
        if (!gpsDataRepository.selectExistsGPSData(gpsDataId)) {
            throw new GPSDataNotFoundException(
                    "GPSData with id " + gpsDataId + "does not exists");
        }
    }

    public void updateGPSData(GPSData gpsData) {
        if (!gpsDataRepository.selectExistsGPSData(gpsData.getId())) {
            throw new GPSDataNotFoundException(
                    "GPSData with id " + gpsData.getId() + "does not exists");
        }

        if (!gpsDataRepository.selectExistsDevice(gpsData.getDeviceId())) {
            throw new BadRequestException(
                    "GPSData device not found with id " + gpsData.getDeviceId());
        }

        gpsDataRepository.updateGPSData(gpsData.getId(),
                gpsData.getDeviceId(),
                gpsData.getLongitude(),
                gpsData.getLatitude(),
                gpsData.getLocalDateTime());
    }
}
