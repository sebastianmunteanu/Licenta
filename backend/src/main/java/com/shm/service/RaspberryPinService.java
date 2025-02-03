package com.shm.service;

import org.springframework.stereotype.Service;

import com.shm.dto.DigitalSensorPi;
import com.shm.model.RaspberryPinEvent;
import com.shm.repository.RaspberryPiEventURepository;
import com.shm.utils.ErrorMessages;

@Service
public class RaspberryPinService {

    private ErrorMessages errorMessages;
    private RaspberryPiEventURepository raspberryEvent;

    RaspberryPinService(RaspberryPiEventURepository raspberryEvent) {
        this.raspberryEvent = raspberryEvent;
    }

    public void saveRasperryPiEvent(DigitalSensorPi digitalSensor) {
        String message = errorMessages.PIN_NOT_CONFIGURE;
        RaspberryPinEvent event = new RaspberryPinEvent();
        event.setPinNumber(digitalSensor.getSensorPin());
        event.setErrorMessage(message);
        raspberryEvent.save(event);
    }
}
