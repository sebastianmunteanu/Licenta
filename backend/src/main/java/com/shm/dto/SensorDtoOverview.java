package com.shm.dto;

import java.util.List;

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
public class SensorDtoOverview {
    private Long id;
    private String name;
    private String place;
    private int alertsToday;
    private String status;
    private List<String> smsDestinatary;
    private String onMessage;
    private String offMessage;
    private String type;
    private String sensorPin;
}
