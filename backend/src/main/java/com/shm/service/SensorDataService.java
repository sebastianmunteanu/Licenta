package com.shm.service;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.Queue;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.shm.dto.SmsDto;
import com.shm.model.Event;
import com.shm.model.Sensor;
import com.shm.model.SensorEvent;
import com.shm.model.Sms;
import com.shm.model.SmsAlert;

//http://192.168.1.10:5000/receive-data
//http://188.24.137.69:2000/receive-data

@Service
public class SensorDataService {

    private SensorEventService sensorEventService;
    private SmsService smsService;
    private EventService eventService;
    private final Queue<Sms> smsList = new LinkedList<>();
    private final String raspberryEndpoint = new String("http://192.168.1.10:5000/receive-data");

    SensorDataService(SensorEventService sensorEventService, SmsService smsService, EventService eventService) {
        this.sensorEventService = sensorEventService;
        this.smsService = smsService;
        this.eventService = eventService;
    }

    public void processRecivedAlert(Sensor sensor, boolean isOn) {
        if (sensor.isActiv()) {
            generateEvent(sensor, isOn);
            checkAndSendSms(sensor, isOn);
        }
    }

    private void generateEvent(Sensor sensor, boolean isOn) {
        Event event = new Event();
        SensorEvent sensorEvent = new SensorEvent();
        String eventName = isOn ? sensor.getActiveMessage() : sensor.getInactivMessage();

        event.setEventName(eventName);
        event.setEventDate(LocalDateTime.now());
        event.setEventPlace(sensor.getLocalizare());
        eventService.saveEvent(event);

        sensorEvent.setEventDate(LocalDateTime.now());
        sensorEvent.setSensor(sensor);
        sensorEventService.saveEvent(sensorEvent);
    }

    private void checkAndSendSms(Sensor sensor, boolean isOn) {
        if (sensor.getSmsAlert().isEmpty()) {
            return;
        }

        for (SmsAlert smsAlert : sensor.getSmsAlert()) {
            Sms sms = new Sms();
            sms.setPhoneNumber(smsAlert.getPhoneNumber());
            String smsEventName = isOn ? sensor.getActiveMessage() : sensor.getInactivMessage();
            String message = sensor.getDenumire() + ": " + sensor.getLocalizare() + "-" + smsEventName;
            sms.setMessage(message);
            smsList.add(sms);
        }
    }

    @Scheduled(fixedRate = 1000)
    private void sendQueuedMessages() {
        while (!smsList.isEmpty()) {
            Sms sms = smsList.poll();
            sms.setSmsDate(LocalDateTime.now());
            if (sendSMS(smsService.getSmsToSend(sms))) {
                try {
                    Thread.sleep(3000);
                    smsService.saveSms(sms);
                } catch (InterruptedException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    private boolean sendSMS(SmsDto sms) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<SmsDto> request = new HttpEntity<>(sms, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(raspberryEndpoint, request, String.class);
        if (response.getStatusCode() == HttpStatus.OK) {
            System.out.println("Date trimise cu succes catre Raspberry Pi!");
            return true;
        } else {
            System.out.println("Eroare la trimiterea datelor către Raspberry Pi: ");
            System.out.println(response.getStatusCode());
            return false;
        }
    }
}
