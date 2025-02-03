package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.SensorPinService;

@RestController
public class SensorPinControllerR {

    private final SensorPinService sensorPinService;

    SensorPinControllerR(SensorPinService sensorPinService) {
        this.sensorPinService = sensorPinService;
    }

    @GetMapping("/api/sensors-pin/")
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        Map<String, Object> response = new HashMap<>();
        response.put("sensorspin", sensorPinService.getAllSensorPinFree());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/pins-all/")
    public ResponseEntity<Map<String, Object>> getAllPins() {
        Map<String, Object> response = new HashMap<>();
        response.put("sensorspin", sensorPinService.getAllSensorPin());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/pins-all-warning/")
    public ResponseEntity<Map<String, Object>> getAllWarning() {
        Map<String, Object> response = new HashMap<>();
        response.put("warningpins", sensorPinService.getPinsNotConfigure());
        return ResponseEntity.ok(response);
    }

}
