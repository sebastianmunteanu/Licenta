package com.shm.dto;

import java.util.List;

import com.shm.model.SmsAlert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class SensorDtoPrint {
    private Long id;
    private String denumire;
    private String localizare;
    private int pinRaspberry;
    private int numarAlerte;
    private List<SmsAlert> smsAlerts;
}
