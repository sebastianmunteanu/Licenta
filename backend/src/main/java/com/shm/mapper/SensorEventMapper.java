package com.shm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shm.dto.SensorEventDto;
import com.shm.model.SensorEvent;;

@Component
public class SensorEventMapper {

    public List<SensorEventDto> getAllSensorEvents(List<SensorEvent> sensorEventList) {
        return sensorEventList.stream()
                .map(event -> mapToSensorEventDto(event))
                .collect(Collectors.toList());
    }

    public SensorEventDto getEventSensorDto(SensorEvent event) {
        return mapToSensorEventDto(event);
    }

    public SensorEventDto mapToSensorEventDto(SensorEvent event) {
        return SensorEventDto.builder()
                .eventDate(getDate(event))
                .eventTime(getTime(event))
                .localizare(event.getSensor().getLocalizare())
                .build();
    }

    private String getDate(SensorEvent sensorEvent) {
        return String.valueOf(sensorEvent.getEventDate().getDayOfMonth()) + "-" +
                String.valueOf(sensorEvent.getEventDate().getMonth()) + "-" +
                String.valueOf(sensorEvent.getEventDate().getYear());
    }

    private String getTime(SensorEvent sensorEvent) {
        return String.valueOf(sensorEvent.getEventDate().getHour()) + ":" +
                String.valueOf(sensorEvent.getEventDate().getMinute()) + ":" +
                String.valueOf(sensorEvent.getEventDate().getSecond());
    }
}
