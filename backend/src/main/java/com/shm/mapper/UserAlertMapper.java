package com.shm.mapper;

import org.springframework.stereotype.Component;

import com.shm.dto.SensorDtoPi;
import com.shm.model.Sensor;
import com.shm.model.UserAlert;

@Component
public class UserAlertMapper {
    public UserAlert getUserAlert(Sensor sensor, SensorDtoPi sensorDtoPi) {
        return mapToUserAlert(sensor, sensorDtoPi);
    }

    public UserAlert mapToUserAlert(Sensor sensor, SensorDtoPi sensorDtoPi) {
        return UserAlert.builder()
                .phoneNumber(sensor.getSmsAlert().get(0).getPhoneNumber())
                .locatie(sensor.getLocalizare())
                .alertMessage(sensorDtoPi.getSensorAllert())
                .build();
    }
}
