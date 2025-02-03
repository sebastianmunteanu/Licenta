package com.shm.mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shm.dto.SmsDto;
import com.shm.model.Sms;;

@Component
public class SmsMapper {

    public List<SmsDto> getSmsDtoList(List<Sms> smsList) {
        return smsList.stream()
                .map(sms -> mapToSmsDtoFromSms(sms))
                .collect(Collectors.toList());
    }

    public SmsDto getSmsToSend(Sms sms) {
        return mapToSmsDtoFromSms(sms);
    }

    public Sms getSmsFromSmsDto(SmsDto smsDto) {
        return mapToSmsFromSmsDto(smsDto);
    }

    private SmsDto mapToSmsDtoFromSms(Sms sms) {
        return SmsDto.builder()
                .message(sms.getMessage())
                .smsDate(getDate(sms.getSmsDate()))
                .smsTime(getTime(sms.getSmsDate()))
                .phoneNumber(sms.getPhoneNumber())
                .build();
    }

    private Sms mapToSmsFromSmsDto(SmsDto smsDto) {
        return Sms.builder()
                .message(smsDto.getMessage())
                .phoneNumber(smsDto.getPhoneNumber())
                .build();
    }

    private String getDate(LocalDateTime date) {
        return String.valueOf(date.getDayOfMonth()) + "-" +
                String.valueOf(date.getMonth()) + "-" +
                String.valueOf(date.getYear());
    }

    private String getTime(LocalDateTime time) {
        return String.valueOf(time.getHour()) + ":" +
                String.valueOf(time.getMinute()) + ":" +
                String.valueOf(time.getSecond());
    }
}
