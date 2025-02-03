package com.shm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.shm.dto.SmsAlertDto;
import com.shm.service.SensorEventService;
import com.shm.service.SensorService;
import com.shm.service.SmsAlertService;

import jakarta.validation.Valid;

@Controller
public class AlertController {

    private SensorEventService sensorEventService;
    private SmsAlertService smsAlertService;
    private SensorService sensorService;

    AlertController(SensorEventService sensorEventService, SmsAlertService smsAlertService,
            SensorService sensorService) {
        this.sensorEventService = sensorEventService;
        this.smsAlertService = smsAlertService;
        this.sensorService = sensorService;
    }

    @GetMapping(value = "/alerts")
    public String getAlertsList(Model model) {
        model.addAttribute("eventList", sensorEventService.getAllSensorAlerts());
        return "alerts";
    }

    @GetMapping("/alertsactions")
    public String getAlertsActions(Model model) {
        model.addAttribute("sensorList", sensorService.getAllSensor());
        model.addAttribute("smsalert", new SmsAlertDto());
        return "alertsactions";
    }

    @PostMapping("/addAlertToSensor")
    public String addAlertToSensor(@ModelAttribute("smsalert") @Valid SmsAlertDto smsAlertDto,
            BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("sensor", sensorService.findSensorDtoPrint(smsAlertDto.getSensorId()));
            return "editalert";
        }

        smsAlertService.addAlertToSensor(smsAlertDto);
        return "redirect:/activesensors";
    }

    @PostMapping("/deleteAlertFromSensor/{id}")
    public String deleteAlertFromSensor(@PathVariable("id") Long alertId) {
        smsAlertService.deleteAlertFromSensor(alertId);
        return "redirect:/activesensors";
    }

    @GetMapping(value = "/editAlert")
    public String editAlert(Model model, @ModelAttribute("id") Long id) {
        model.addAttribute("smsalert", new SmsAlertDto(id));
        model.addAttribute("sensor", sensorService.findSensorDtoPrint(id));
        return "editalert";
    }

}
