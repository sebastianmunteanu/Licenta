package com.shm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shm.model.RaspberryPinEvent;

@Repository
public interface RaspberryPiEventURepository extends JpaRepository<RaspberryPinEvent, Long> {
}
