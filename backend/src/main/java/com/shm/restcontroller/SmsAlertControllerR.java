package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shm.dto.SmsAlertDto;
import com.shm.service.SmsAlertService;

@RestController
public class SmsAlertControllerR {
    private final SmsAlertService smsAlertService;

    public SmsAlertControllerR(SmsAlertService smsAlertService) {
        this.smsAlertService = smsAlertService;
    }

    @GetMapping("/api/smsalertsforsensor/{sensorId}")
    public ResponseEntity<Map<String, Object>> getSmsAlertsForSensor(@PathVariable int sensorId) {
        Map<String, Object> response = new HashMap<>();
        response.put("smsAlerts", smsAlertService.getAllSmsListForSensor(sensorId));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/deletesmsalert/{alertId}")
    public ResponseEntity<String> enableeSensor(@PathVariable Long alertId) {
        smsAlertService.deleteAlertFromSensor(alertId);
        boolean isDisabled = true;
        if (isDisabled) {
            return ResponseEntity.ok("Sensor disabled successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found or already disabled");
        }
    }

    @PostMapping("/api/addalertsmstosensor")
    public ResponseEntity<String> addAlertToSensor(@RequestBody SmsAlertDto smsAlert) {
        smsAlertService.addAlertSmsToSensor(smsAlert);
        boolean isDisabled = true;
        if (isDisabled) {
            return ResponseEntity.ok("Sensor disabled successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found or already disabled");
        }
    }

}
