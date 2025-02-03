package com.shm.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shm.model.Sms;

@Repository
public interface SmsRepository extends JpaRepository<Sms, Long> {
    List<Sms> findAllBySmsDateBetween(LocalDateTime startOfDay, LocalDateTime endOfDay);
}
