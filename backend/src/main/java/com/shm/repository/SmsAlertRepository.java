package com.shm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shm.model.SmsAlert;

@Repository
public interface SmsAlertRepository extends JpaRepository<SmsAlert, Long> {

    List<SmsAlert> findBySensorId(int sensorId);
}
