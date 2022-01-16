package com.example.georest.repository;

import com.example.georest.model.GPSData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface GPSDataRepository extends JpaRepository<GPSData, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(g) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM GPSData g " +
            "WHERE g.id = ?1")
    Boolean selectExistsGPSData(Long id);

    @Query("" +
            "SELECT CASE WHEN COUNT(d) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Device d " +
            "WHERE d.id = ?1")
    Boolean selectExistsDevice(Long deviceId);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying(clearAutomatically = true)
    @Query("UPDATE GPSData " +
            "SET deviceId = ?2, " +
            "longitude = ?3, " +
            "latitude = ?4, " +
            "localDateTime = ?5 " +
            "WHERE id = ?1")
    void updateGPSData(Long id,
                      Long deviceId,
                      Double longitude,
                      Double latitude,
                      LocalDateTime localDateTime);

    @Query("SELECT g FROM GPSData g " +
            "WHERE g.deviceId = ?1")
    List<GPSData> selectGPSDataListByDeviceId(Long deviceId);
}
