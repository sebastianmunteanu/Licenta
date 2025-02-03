package com.shm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shm.model.PinEvent;
import com.shm.model.SensorPin;
import com.shm.repository.PinEventRepository;

@Service
public class PinEventService {

    private PinEventRepository pinEventRepository;
    private SensorPinService sensorPinService;

    PinEventService(PinEventRepository pinEventRepository, SensorPinService sensorPinService) {
        this.pinEventRepository = pinEventRepository;
        this.sensorPinService = sensorPinService;
    }

    public PinEvent getPinEvent(Long id) {
        return pinEventRepository.findById(id).orElse(null);
    }

    public List<PinEvent> getAllPinEvents() {
        return pinEventRepository.findAll();
    }

    public void deletePinEvent(Long id) {
        pinEventRepository.deleteById(id);
    }

    public void savePinEvent(int pinNumber) {
        SensorPin sensorPin = sensorPinService.getSensorPin(pinNumber);
        if (!sensorPin.isFree()) {
            return;
        }
        PinEvent pinEvent = pinEventRepository.findByPinNumber(pinNumber);
        if (pinEvent == null) {
            PinEvent pinEventSaved = pinEventRepository.save(new PinEvent(pinNumber));
            sensorPin.setPinEvent(pinEventSaved);
            sensorPinService.saveSensorPin(sensorPin);
        }
    }
}
