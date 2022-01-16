package com.example.georest.repository;

import com.example.georest.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query("" +
            "SELECT CASE WHEN COUNT(d) > 0 THEN " +
            "TRUE ELSE FALSE END " +
            "FROM Device d " +
            "WHERE d.name = ?1"
    )
    Boolean selectExistsDevice(String name);

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Modifying(clearAutomatically = true)
    @Query("UPDATE Device " +
            "SET name = ?2 " +
            "WHERE id = ?1")
    void updateDevice(Long id, String name);
}
