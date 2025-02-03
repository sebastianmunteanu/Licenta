package com.shm.service;

import org.springframework.stereotype.Service;

import com.shm.dto.SensorDtoPi;
import com.shm.mapper.UserAlertMapper;
import com.shm.model.Sensor;
import com.shm.model.UserAlert;

@Service
public class UserAlertService {

    private UserAlertMapper userAlertMapper;

    UserAlertService(UserAlertMapper userAlertMapper) {
        this.userAlertMapper = userAlertMapper;
    }

    public UserAlert getAlertToBeSendBySMS(Sensor sensor, SensorDtoPi sensorDtoPi) {
        return userAlertMapper.getUserAlert(sensor, sensorDtoPi);
    }
}
