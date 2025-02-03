package com.shm.dto;

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
@ToString
@Builder
public class SensorDtoToAdd {
    private String denumire;
    private String localizare;
    private String activeMessage;
    private String inactiveMessage;
    private int sensorPin;
    private String sensorType;
}
