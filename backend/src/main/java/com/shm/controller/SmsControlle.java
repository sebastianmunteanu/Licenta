package com.shm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shm.service.SmsService;

@Controller
public class SmsControlle {

    private SmsService smsService;

    SmsControlle(SmsService smsService) {
        this.smsService = smsService;
    }

    @GetMapping("/smslist")
    public String getSmsList(Model model) {
        model.addAttribute("smsList", smsService.getAllSms());
        return "smsList";
    }

}
