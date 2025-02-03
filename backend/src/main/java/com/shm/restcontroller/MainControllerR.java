package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.EventService;
import com.shm.service.SensorEventService;
import com.shm.service.SensorService;
import com.shm.service.SmsService;
import com.shm.utils.Calendar;

@RestController
public class MainControllerR {
    private final SensorService sensorService;
    private final SensorEventService sensorEventService;
    private final SmsService smsService;
    private final EventService eventService;
    private final Calendar calendar;

    public MainControllerR(SensorService sensorService, SensorEventService sensorEventService, SmsService smsService,
            EventService eventService,
            Calendar calendar) {
        this.sensorService = sensorService;
        this.sensorEventService = sensorEventService;
        this.smsService = smsService;
        this.eventService = eventService;
        this.calendar = calendar;
    }

    @GetMapping("/api/navbar-data")
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        Map<String, Object> response = new HashMap<>();
        response.put("sensorNumber", sensorService.getNumberOfSensors());
        response.put("alertsToday", eventService.getNumerOfAlertsToday());
        response.put("smsToday", smsService.getNumberOfSmsToday());
        response.put("inactiveSensors", sensorService.getNumberOfInactiveSensors());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/chart-data/week")
    public ResponseEntity<Map<String, Object>> getChartWeekData() {
        Map<String, Object> response = new HashMap<>();
        response.put("labels", calendar.getWeekDays());
        response.put("data", eventService.getLastWeekAlerts());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/chart-data/year")
    public ResponseEntity<Map<String, Object>> getChartYearData() {
        Map<String, Object> response = new HashMap<>();
        response.put("labels", calendar.getMonths());
        response.put("data", eventService.getLastYearAlerts());
        return ResponseEntity.ok(response);
    }

}
