package com.shm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.shm.model.Sensor;
import com.shm.model.SensorPin;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {
    Sensor findBySensorPin(SensorPin sensorPin);

    Sensor findBySensorPin_PinNumber(int pinNumber);

    @Query("SELECT s FROM Sensor s WHERE s.sensorPin.pinNumber = :pinNumber")
    Sensor findBySensorPinPinNumber(@Param("pinNumber") int pinNumber);

    List<Sensor> findByIsActivTrue();

    List<Sensor> findByIsActivFalse();

    void deleteById(int id);
}
