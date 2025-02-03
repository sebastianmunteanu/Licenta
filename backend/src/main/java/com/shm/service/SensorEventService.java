package com.shm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shm.dto.SensorEventDto;
import com.shm.mapper.SensorEventMapper;
import com.shm.model.SensorEvent;
import com.shm.repository.SensorEventRepository;

@Service
public class SensorEventService {

    private SensorEventRepository sensorEventRepository;
    private SensorEventMapper sensorEventMapper;

    SensorEventService(SensorEventRepository sensorEventRepository, SensorEventMapper sensorEventMapper) {
        this.sensorEventRepository = sensorEventRepository;
        this.sensorEventMapper = sensorEventMapper;
    }

    public void saveEvent(SensorEvent sensorEvent) {
        sensorEventRepository.save(sensorEvent);
    }

    public List<SensorEventDto> getAllSensorAlerts() {
        return sensorEventMapper.getAllSensorEvents(sensorEventRepository.findAll());
    }

    public List<SensorEvent> getAlertsInRange(LocalDateTime start, LocalDateTime end) {
        return sensorEventRepository.findAllByEventDateBetween(start, end);
    }

    public Integer getNumberOfAlertsTodayOfSensorId(Long id, LocalDateTime start, LocalDateTime end) {
        return sensorEventRepository.findAllBySensorIdAndEventDateBetween(id, start, end).size();
    }

    public String getNumberOfAlertsToday() {
        return String.valueOf(getAlertsInRange(LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
                LocalDateTime.of(LocalDate.now(), LocalTime.MAX)).size());
    }

    public List<Integer> getLastWeekAlerts() {
        // definire variabile
        List<Integer> lastWeekAlerts = new ArrayList<>();
        LocalDateTime endOfWeek = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime startOfWeek = LocalDateTime.of(LocalDate.now().minusDays(6), LocalTime.MIN);

        // get alerte din ultimele 7 zile din DB
        List<SensorEvent> sensorEvents = sensorEventRepository.findAllByEventDateBetween(startOfWeek, endOfWeek);

        // definire hashmap si initilaizare
        Map<Integer, Integer> lastWeekValues = new HashMap<>();
        for (int i = 0; i < 7; i++) {
            lastWeekValues.put(LocalDateTime.now().minusDays(i).getDayOfMonth(), 0);
        }

        // parcurgere lista alerte pentru extragere date
        for (SensorEvent sensorEvent : sensorEvents) {
            Integer value = lastWeekValues.get(sensorEvent.getEventDate().getDayOfMonth());
            lastWeekValues.put(sensorEvent.getEventDate().getDayOfMonth(), ++value);
        }

        // construire vector cu valorile alertelor pentru ultimele 7 zile
        for (int i = 0; i < 7; i++) {
            Integer value = lastWeekValues.get(LocalDateTime.now().minusDays(i).getDayOfMonth());
            lastWeekAlerts.addFirst(value);
        }

        return lastWeekAlerts;
    }

    public List<Integer> getLastYearAlerts() {
        List<Integer> lastYearAlerts = new ArrayList<>();

        LocalDateTime endOfYear = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
        LocalDateTime startOfYear = LocalDateTime.of(LocalDate.now().minusMonths(12), LocalTime.MIN);

        List<SensorEvent> sensorEvents = sensorEventRepository.findAllByEventDateBetween(startOfYear, endOfYear);

        HashMap<Integer, Integer> lastYearValues = new HashMap<>();

        for (int i = 0; i < 12; i++) {
            lastYearValues.put(LocalDateTime.now().minusMonths(i).getMonthValue(), 0);
        }

        for (SensorEvent sensorEvent : sensorEvents) {
            Integer value = lastYearValues.get(sensorEvent.getEventDate().getMonthValue());
            lastYearValues.put(sensorEvent.getEventDate().getMonthValue(), ++value);
        }

        for (int i = 0; i < 12; i++) {
            Integer value = lastYearValues.get(LocalDateTime.now().minusMonths(i).getMonthValue());
            lastYearAlerts.addFirst(value);
        }

        return lastYearAlerts;
    }
}
