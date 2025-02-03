package com.shm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SmsDto {
    private String message;
    private String phoneNumber;
    private String smsDate;
    private String smsTime;
}
