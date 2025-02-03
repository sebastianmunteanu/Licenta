package com.shm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shm.dto.SmsAlertDto;
import com.shm.dto.SmsDtoPrint;
import com.shm.mapper.SmsAlertMapper;
import com.shm.model.SmsAlert;
import com.shm.repository.SmsAlertRepository;

@Service
public class SmsAlertService {

    private SmsAlertRepository smsAlertRepository;
    private SmsAlertMapper smsAlertMapper;

    SmsAlertService(SmsAlertRepository smsAlertRepository, SmsAlertMapper smsAlertMapper) {
        this.smsAlertRepository = smsAlertRepository;
        this.smsAlertMapper = smsAlertMapper;
    }

    public void saveSmsAlert(SmsAlert smsAlert) {
        smsAlertRepository.save(smsAlert);
    }

    public List<SmsDtoPrint> getAllSmsListForSensor(int id) {
        return smsAlertMapper.getAllSmsAlerts(smsAlertRepository.findBySensorId(id));
    }

    public void addAlertToSensor(SmsAlertDto smsAlertDto) {
        smsAlertRepository.save(smsAlertMapper.getSmsAlert(smsAlertDto));
    }

    public void addAlertSmsToSensor(SmsAlertDto smsAlertDto) {
        smsAlertRepository.save(smsAlertMapper.getSmsAlert(smsAlertDto));
    }

    public void deleteAlertFromSensor(Long alertId) {
        smsAlertRepository.deleteById(alertId);
    }
}
