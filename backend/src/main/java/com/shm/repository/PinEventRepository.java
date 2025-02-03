package com.shm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shm.model.PinEvent;

@Repository
public interface PinEventRepository extends JpaRepository<PinEvent, Long> {
  PinEvent findByPinNumber(int pinNumber);
}
