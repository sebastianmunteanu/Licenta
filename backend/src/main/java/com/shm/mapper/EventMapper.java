package com.shm.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.shm.dto.EventDto;
import com.shm.model.Event;

@Component
public class EventMapper {

    public List<EventDto> getAllEvents(List<Event> eventList) {
        return eventList.stream()
                .map(event -> mapToEventDto(event))
                .toList();
    }

    public EventDto getEventDto(Event event) {
        return mapToEventDto(event);
    }

    private EventDto mapToEventDto(Event event) {
        return EventDto.builder()
                .eventName(event.getEventName())
                .eventDate(getDate(event))
                .eventTime(getTime(event))
                .eventPlace(event.getEventPlace())
                .build();
    }

    private String getDate(Event sensorEvent) {
        return String.valueOf(sensorEvent.getEventDate().getDayOfMonth()) + "-" +
                String.valueOf(sensorEvent.getEventDate().getMonth()) + "-" +
                String.valueOf(sensorEvent.getEventDate().getYear());
    }

    private String getTime(Event sensorEvent) {
        return String.valueOf(sensorEvent.getEventDate().getHour()) + ":" +
                String.valueOf(sensorEvent.getEventDate().getMinute()) + ":" +
                String.valueOf(sensorEvent.getEventDate().getSecond());
    }
}
