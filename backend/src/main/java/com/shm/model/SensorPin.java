package com.shm.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "t_sensorpin")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SensorPin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pinnumber")
    private int pinNumber;

    @Column(name = "pindescription")
    private String pinDescription;

    @Column(name = "pintype")
    private String pinType;

    @Column(name = "isfree")
    private boolean isFree;

    @OneToOne
    @JoinColumn(name = "pin_event")
    private PinEvent pinEvent;

}
