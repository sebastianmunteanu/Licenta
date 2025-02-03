package com.shm.dto;

import jakarta.validation.constraints.Pattern;
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
public class SmsAlertDto {

    public SmsAlertDto(Long sensorId) {
        this.sensorId = sensorId;
    }

    private Long sensorId;

    @Pattern(regexp = "^\\+?(?:[0-9] ?){6,14}[0-9]$", message = "Introduceți un număr de telefon valid")
    private String phoneNumber;

}
