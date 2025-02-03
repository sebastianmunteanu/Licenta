package com.shm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shm.model.SensorPin;

@Repository
public interface SensorPinRepository extends JpaRepository<SensorPin, Integer> {
    SensorPin findByPinNumber(int pin);

    List<SensorPin> findByIsFreeTrue();

    List<SensorPin> findByPinEventNotNull();

}
