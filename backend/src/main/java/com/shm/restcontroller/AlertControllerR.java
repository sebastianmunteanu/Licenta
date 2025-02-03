package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.SensorEventService;

@RestController
public class AlertControllerR {
  private final SensorEventService sensorEventService;

  public AlertControllerR(SensorEventService sensorEventService) {
    this.sensorEventService = sensorEventService;
  }

  @GetMapping("/api/sensor-alerts")
  public ResponseEntity<Map<String, Object>> getSensorAlerts() {
    Map<String, Object> response = new HashMap<>();
    response.put("sensorAlerts", sensorEventService.getAllSensorAlerts());
    return ResponseEntity.ok(response);
  }

}
