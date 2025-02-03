package com.shm.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Builder
@Table(name = "t_sensor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotBlank
    private String denumire;

    @NotBlank
    @Column
    private String localizare;

    @Column
    private boolean isActiv;

    @Column
    private String sensorType;

    @NotBlank
    @Column
    private String activeMessage;

    @NotBlank
    @Column(name = "inactive_message")
    private String inactivMessage;

    @OneToOne
    @JoinColumn(name = "sensorpin_id")
    private SensorPin sensorPin;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sensorId")
    private List<SmsAlert> smsAlert;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sensor_id")
    private List<SensorEvent> sensorEvents;

}
