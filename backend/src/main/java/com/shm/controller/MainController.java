package com.shm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shm.service.SensorEventService;
import com.shm.service.SensorService;
import com.shm.service.SmsService;

@Controller
public class MainController {

    private SensorService sensorService;
    private SensorEventService sensorEventService;
    private SmsService smsService;

    MainController(SensorService sensorService, SensorEventService sensorEventService, SmsService smsService) {
        this.sensorService = sensorService;
        this.sensorEventService = sensorEventService;
        this.smsService = smsService;
    }

    @GetMapping("/index")
    public String getSite(Model model) {
        model.addAttribute("sensorNumber", sensorService.getNumberOfSensors());
        model.addAttribute("alertsToday", sensorEventService.getNumberOfAlertsToday());
        model.addAttribute("smsToday", smsService.getNumberOfSmsToday());
        return "index";
    }

}
