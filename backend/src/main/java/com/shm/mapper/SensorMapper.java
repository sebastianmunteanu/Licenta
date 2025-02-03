package com.shm.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.shm.dto.SensorDto;
import com.shm.dto.SensorDtoAddAlert;
import com.shm.dto.SensorDtoOverview;
import com.shm.dto.SensorDtoPi;
import com.shm.dto.SensorDtoPrint;
import com.shm.dto.SensorDtoToAdd;
import com.shm.model.Sensor;
import com.shm.model.SensorPin;
import com.shm.model.SmsAlert;
import com.shm.repository.SensorPinRepository;
import com.shm.service.SensorEventService;
import com.shm.utils.Calendar;

@Component
public class SensorMapper {

    private final SensorEventService sensorEventService;
    private final SensorPinRepository sensorPinRepository;

    SensorMapper(SensorEventService sensorEventService, SensorPinRepository sensorPinRepository) {
        this.sensorEventService = sensorEventService;
        this.sensorPinRepository = sensorPinRepository;
    }

    public Sensor getSenzor(SensorDtoPi senzorDtoPi) {
        return mapToSenzor(senzorDtoPi);
    }

    public Sensor getSensorFromSensorDto(SensorDto sensorDto) {
        return mapToSensorFromSensorDto(sensorDto);
    }

    public Sensor getSensorFromSensorDtoToAdd(SensorDtoToAdd sensorDtoToAdd, SensorPin sensorPin) {
        return mapToSensorFromSensorDtoToAdd(sensorDtoToAdd, sensorPin);
    }

    public SensorDtoOverview getSensorDtoOverviewFromSensor(Sensor sensor) {
        return mapToSensorDtoOverviewFromSensor(sensor);
    }

    public List<SensorDtoAddAlert> getAllSensor(List<Sensor> sensorList) {
        return sensorList.stream()
                .map(sensor -> mapToSensorDtoAddAlert(sensor))
                .collect(Collectors.toList());
    }

    public List<SensorDtoPrint> getListOfSensors(List<Sensor> sensorList) {
        return sensorList.stream()
                .map(sensor -> mapFromSensorToSensorDtoPrint(sensor))
                .collect(Collectors.toList());
    }

    public List<SensorDtoOverview> getAllSensorDtoOverviews(List<Sensor> sensors) {
        return sensors.stream()
                .map((sensor) -> mapToSensorDtoOverviewFromSensor(sensor))
                .collect(Collectors.toList());
    }

    private Sensor mapToSenzor(SensorDtoPi senzorDtoPi) {
        return Sensor.builder()
                .sensorPin(sensorPinRepository.findByPinNumber(senzorDtoPi.getSensorPin()))
                .build();
    }

    private Sensor mapToSensorFromSensorDto(SensorDto sensorDto) {
        return Sensor.builder()
                .id(sensorDto.getId())
                .denumire(sensorDto.getDenumire())
                .localizare(sensorDto.getLocalizare())
                .sensorPin(sensorPinRepository.findByPinNumber(sensorDto.getPinNumber()))
                .isActiv(sensorDto.isActiv())
                .build();
    }

    private SensorDtoAddAlert mapToSensorDtoAddAlert(Sensor sensor) {
        return SensorDtoAddAlert.builder()
                .id(sensor.getId())
                .fullName(sensor.getDenumire() + " - " + sensor.getLocalizare())
                .build();
    }

    public SensorDtoPrint mapFromSensorToSensorDtoPrint(Sensor sensor) {
        return SensorDtoPrint.builder()
                .id(sensor.getId())
                .denumire(sensor.getDenumire())
                .localizare(sensor.getLocalizare())
                .pinRaspberry(sensor.getSensorPin() == null ? 0 : sensor.getSensorPin().getPinNumber())
                .numarAlerte(sensor.getSmsAlert().size())
                .smsAlerts(sensor.getSmsAlert())
                .build();
    }

    private Sensor mapToSensorFromSensorDtoToAdd(SensorDtoToAdd sensorDtoToAdd, SensorPin sensorPin) {
        return Sensor.builder()
                .denumire(sensorDtoToAdd.getDenumire())
                .localizare(sensorDtoToAdd.getLocalizare())
                .activeMessage(sensorDtoToAdd.getActiveMessage())
                .inactivMessage(sensorDtoToAdd.getInactiveMessage())
                .sensorType(sensorDtoToAdd.getSensorType())
                .sensorPin(sensorPin)
                .isActiv(false)
                .build();
    }

    public SensorDtoOverview mapToSensorDtoOverviewFromSensor(Sensor sensor) {
        List<String> phoneNumbers = sensor.getSmsAlert().stream().map(SmsAlert::getPhoneNumber)
                .collect(Collectors.toList());
        Integer numberOfAlertsToday = sensorEventService.getNumberOfAlertsTodayOfSensorId(sensor.getId(),
                Calendar.startDay(0), Calendar.NOW);
        return SensorDtoOverview.builder()
                .id(sensor.getId())
                .name(sensor.getDenumire())
                .place(sensor.getLocalizare())
                .status(sensor.isActiv() ? "ONLINE" : "OFFLINE")
                .alertsToday(numberOfAlertsToday)
                .smsDestinatary(phoneNumbers)
                .onMessage(sensor.getActiveMessage())
                .offMessage(sensor.getInactivMessage())
                .type(sensor.getSensorType())
                .sensorPin(sensor.getSensorPin().getPinDescription())
                .build();
    }

}
