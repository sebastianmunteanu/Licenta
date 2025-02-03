package com.shm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.shm.dto.SmsAlertDto;
import com.shm.dto.SmsDtoPrint;
import com.shm.model.SmsAlert;
import com.shm.service.SensorService;

@Component
public class SmsAlertMapper {

    @Autowired
    SensorService sensorService;

    public SmsAlert getSmsAlert(SmsAlertDto smsAlertDto) {
        return mapToSmsAlert(smsAlertDto);
    }

    public SmsDtoPrint getSmsDtoPrint(SmsAlert smsAlert) {
        return mapToSmsDtoPrint(smsAlert);
    }

    public List<SmsDtoPrint> getAllSmsAlerts(List<SmsAlert> smsAlerts) {
        return smsAlerts.stream()
                .map((smsAlert) -> mapToSmsDtoPrint(smsAlert))
                .collect(Collectors.toList());
    }

    private SmsAlert mapToSmsAlert(SmsAlertDto smsAlertDto) {
        return SmsAlert.builder()
                .phoneNumber(smsAlertDto.getPhoneNumber())
                .sensor(sensorService.findSensor(smsAlertDto.getSensorId()))
                .build();
    }

    private SmsDtoPrint mapToSmsDtoPrint(SmsAlert smsAlert) {
        return SmsDtoPrint.builder()
                .id(smsAlert.getId())
                .sensorId(smsAlert.getSensor().getId())
                .phoneNumber(smsAlert.getPhoneNumber())
                .build();
    }
}
