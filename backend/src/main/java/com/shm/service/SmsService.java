package com.shm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.shm.dto.SmsDto;
import com.shm.mapper.SmsMapper;
import com.shm.model.Sms;
import com.shm.repository.SmsRepository;

@Service
public class SmsService {

    private SmsMapper smsMapper;
    private SmsRepository smsRepository;

    SmsService(SmsMapper smsMapper, SmsRepository smsRepository) {
        this.smsMapper = smsMapper;
        this.smsRepository = smsRepository;
    }

    public SmsDto getSmsToSend(Sms sms) {
        return smsMapper.getSmsToSend(sms);
    }

    public void saveSms(SmsDto sms) {
        smsRepository.save(smsMapper.getSmsFromSmsDto(sms));
    }

    public void saveSms(Sms sms) {
        smsRepository.save(sms);
    }

    public List<SmsDto> getAllSms() {
        return smsMapper.getSmsDtoList(smsRepository.findAll());
    }

    public String getNumberOfSmsToday() {
        LocalDateTime startOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MIN);
        LocalDateTime endOfDay = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        return String.valueOf(smsRepository.findAllBySmsDateBetween(startOfDay, endOfDay).size());
    }

}
