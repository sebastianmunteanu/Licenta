package com.shm.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.shm.dto.EventDto;
import com.shm.mapper.EventMapper;
import com.shm.model.Event;
import com.shm.repository.EventRepository;

@Service
public class EventService {
  private EventRepository eventRepository;
  private EventMapper eventMapper;

  EventService(EventRepository eventRepository, EventMapper eventMapper) {
    this.eventRepository = eventRepository;
    this.eventMapper = eventMapper;
  }

  public List<EventDto> getAllEvents() {
    return eventMapper.getAllEvents(eventRepository.findAll());
  }

  public void saveEvent(Event event) {
    eventRepository.save(event);
  }

  public String getNumerOfAlertsToday() {
    return String.valueOf(getAlertsInRange(LocalDateTime.of(LocalDate.now(), LocalTime.MIN),
        LocalDateTime.of(LocalDate.now(), LocalTime.MAX)).size());
  }

  private List<Event> getAlertsInRange(LocalDateTime start, LocalDateTime end) {
    return eventRepository.findAllByEventDateBetween(start, end);
  }

  public List<Integer> getLastWeekAlerts() {
    // definire variabile
    List<Integer> lastWeekAlerts = new ArrayList<>();
    LocalDateTime endOfWeek = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    LocalDateTime startOfWeek = LocalDateTime.of(LocalDate.now().minusDays(6), LocalTime.MIN);

    // get alerte din ultimele 7 zile din DB
    List<Event> events = eventRepository.findAllByEventDateBetween(startOfWeek, endOfWeek);

    // definire hashmap si initilaizare
    Map<Integer, Integer> lastWeekValues = new HashMap<>();
    for (int i = 0; i < 7; i++) {
      lastWeekValues.put(LocalDateTime.now().minusDays(i).getDayOfMonth(), 0);
    }

    // parcurgere lista alerte pentru extragere date
    for (Event event : events) {
      Integer value = lastWeekValues.get(event.getEventDate().getDayOfMonth());
      lastWeekValues.put(event.getEventDate().getDayOfMonth(), ++value);
    }

    // construire vector cu valorile alertelor pentru ultimele 7 zile
    for (int i = 0; i < 7; i++) {
      Integer value = lastWeekValues.get(LocalDateTime.now().minusDays(i).getDayOfMonth());
      lastWeekAlerts.addFirst(value);
    }

    return lastWeekAlerts;
  }

  public List<Integer> getLastYearAlerts() {
    List<Integer> lastYearAlerts = new ArrayList<>();

    LocalDateTime endOfYear = LocalDateTime.of(LocalDate.now(), LocalTime.MAX);
    LocalDateTime startOfYear = LocalDateTime.of(LocalDate.now().minusMonths(12), LocalTime.MIN);

    List<Event> events = eventRepository.findAllByEventDateBetween(startOfYear, endOfYear);

    HashMap<Integer, Integer> lastYearValues = new HashMap<>();

    for (int i = 0; i < 12; i++) {
      lastYearValues.put(LocalDateTime.now().minusMonths(i).getMonthValue(), 0);
    }

    for (Event event : events) {
      Integer value = lastYearValues.get(event.getEventDate().getMonthValue());
      lastYearValues.put(event.getEventDate().getMonthValue(), ++value);
    }

    for (int i = 0; i < 12; i++) {
      Integer value = lastYearValues.get(LocalDateTime.now().minusMonths(i).getMonthValue());
      lastYearAlerts.addFirst(value);
    }

    return lastYearAlerts;
  }

}
