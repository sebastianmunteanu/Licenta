package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shm.dto.SensorDtoToAdd;
import com.shm.service.SensorService;

@RestController
public class SensorControllerR {
    private final SensorService sensorService;

    public SensorControllerR(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping("/api/sensors-data")
    public ResponseEntity<Map<String, Object>> getDashboardData() {
        Map<String, Object> response = new HashMap<>();
        response.put("sensors", sensorService.getAllSensorsDtoOverview());
        return ResponseEntity.ok(response);
    }

    @PutMapping("/api/sensors-add")
    public ResponseEntity<String> addNewSensor(@RequestBody SensorDtoToAdd newSensor) {
        if (sensorService.addNewSensor(newSensor)) {
            return ResponseEntity.ok("Sensor disabled successfully");
        }
        return ResponseEntity.status(404).body("Sensor not found or already disabled");
    }

    @PutMapping("/api/sensors-delete/{sensorId}")
    public ResponseEntity<String> deleteSensor(@PathVariable Long sensorId) {
        sensorService.deleteSensor(sensorId);
        boolean isDisabled = true;
        if (isDisabled) {
            return ResponseEntity.ok("Sensor disabled successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found or already disabled");
        }
    }

    @PutMapping("/api/sensors/{sensorId}/disable")
    public ResponseEntity<String> disableSensor(@PathVariable Long sensorId) {
        sensorService.disableSensor(sensorId);
        boolean isDisabled = true;
        if (isDisabled) {
            return ResponseEntity.ok("Sensor disabled successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found or already disabled");
        }
    }

    @PutMapping("/api/sensors/{sensorId}/enable")
    public ResponseEntity<String> enableeSensor(@PathVariable Long sensorId) {
        sensorService.enableSensor(sensorId);
        boolean isDisabled = true;
        if (isDisabled) {
            return ResponseEntity.ok("Sensor disabled successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found or already disabled");
        }
    }

    @PutMapping("/api/sensors/editOnMessage/{sensorId}")
    public ResponseEntity<String> editOnMessageSensor(@PathVariable Long sensorId, @RequestBody String payload) {
        if (sensorService.editOnMessage(sensorId, payload)) {
            return ResponseEntity.ok("Sensor edit on message successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found");
        }
    }

    @PutMapping("/api/sensors/editOffMessage/{sensorId}")
    public ResponseEntity<String> editOffMessageSensor(@PathVariable Long sensorId, @RequestBody String payload) {
        if (sensorService.editOffMessage(sensorId, payload)) {
            return ResponseEntity.ok("Sensor edit off successfully");
        } else {
            return ResponseEntity.status(404).body("Sensor not found");
        }
    }
}
