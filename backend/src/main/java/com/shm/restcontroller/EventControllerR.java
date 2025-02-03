package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.EventService;

@RestController
public class EventControllerR {
  private final EventService eventService;

  public EventControllerR(EventService eventService) {
    this.eventService = eventService;
  }

  @GetMapping("/api/sensor-alerts-all")
  public ResponseEntity<Map<String, Object>> getAlerts() {
    Map<String, Object> response = new HashMap<>();
    response.put("sensorAlerts", eventService.getAllEvents());
    return ResponseEntity.ok(response);
  }
}
