package com.shm.restcontroller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.SmsService;

@RestController
public class SmsControlleR {

    private SmsService smsService;

    SmsControlleR(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/api/smsalert-all")
    public ResponseEntity<Map<String, Object>> getAllSmsAlerts() {
        Map<String, Object> response = new HashMap<>();
        response.put("smsList", smsService.getAllSms());
        return ResponseEntity.ok(response);
    }

}
