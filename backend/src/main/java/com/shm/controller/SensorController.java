package com.shm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.shm.dto.SensorDto;
import com.shm.service.SensorPinService;
import com.shm.service.SensorService;

@Controller
public class SensorController {

    private SensorPinService sensorPinService;
    private SensorService sensorService;

    SensorController(SensorPinService sensorPinService, SensorService sensorService) {
        this.sensorPinService = sensorPinService;
        this.sensorService = sensorService;
    }

    @GetMapping(value = "/sensorsactions")
    public String sensorActions(Model model) {
        model.addAttribute("sensorPinList", sensorPinService.getAllSensorPinFree());
        model.addAttribute("sensor", new SensorDto());
        return "sensorsactions";
    }

    @GetMapping(value = "/activesensors")
    public String getActiveSensorList(Model model) {
        model.addAttribute("sensorList", sensorService.getAllActiveSensorsDto());
        return "activesensors";
    }

    @GetMapping(value = "/inactivesensors")
    public String getInactiveSensorList(Model model) {
        model.addAttribute("sensorList", sensorService.getAllInActiveSensorsDto());
        return "inactivesensors";
    }

    @PostMapping("/addsensor")
    public String submitSensor(@ModelAttribute("sensor") SensorDto sensorDto) {
        sensorService.saveSensor(sensorDto);
        return "redirect:/activesensors";
    }

    @PostMapping("/disableSensor")
    public String disableSensor(@ModelAttribute("id") Long sensorId) {
        sensorService.disableSensor(sensorId);
        return "redirect:/inactivesensors";
    }

    @PostMapping("/enableSensor")
    public String enableSensor(@ModelAttribute("id") Long sensorId) {
        sensorService.enableSensor(sensorId);
        return "redirect:/activesensors";
    }

}
