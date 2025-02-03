package com.shm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shm.model.SensorPin;
import com.shm.repository.SensorPinRepository;

@Service
public class SensorPinService {

    private SensorPinRepository sensorPinRepository;

    SensorPinService(SensorPinRepository sensorPinRepository) {
        this.sensorPinRepository = sensorPinRepository;
    }

    public List<SensorPin> getAllSensorPin() {
        return sensorPinRepository.findAll();
    }

    public List<SensorPin> getAllSensorPinFree() {
        return sensorPinRepository.findByIsFreeTrue();
    }

    public SensorPin getSensorPin(int pinNumber) {
        return sensorPinRepository.findByPinNumber(pinNumber);
    }

    public void saveSensorPin(SensorPin sensorPin) {
        sensorPinRepository.save(sensorPin);
    }

    public List<SensorPin> getPinsNotConfigure() {
        return sensorPinRepository.findByPinEventNotNull();
    }
}
