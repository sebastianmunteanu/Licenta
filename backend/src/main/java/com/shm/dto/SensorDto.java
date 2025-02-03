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
public class SensorDto {
    private Long id;
    private String denumire;
    private String localizare;
    private int pinNumber;
    private boolean isActiv;
}
