package com.shm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shm.model.SensorEvent;

@Repository
public interface SensorEventRepository extends JpaRepository<SensorEvent, Long> {
    List<SensorEvent> findAllByEventDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);

    List<SensorEvent> findAllBySensorIdAndEventDateBetween(Long sensorId, LocalDateTime startDate,
            LocalDateTime endDate);
}
