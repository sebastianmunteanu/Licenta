package com.shm.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class EventDto {
    private String eventName;
    private String eventDate;
    private String eventTime;
    private String eventPlace;
}
