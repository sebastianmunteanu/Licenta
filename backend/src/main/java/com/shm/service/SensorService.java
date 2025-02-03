package com.shm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.shm.dto.SensorDto;
import com.shm.dto.SensorDtoAddAlert;
import com.shm.dto.SensorDtoOverview;
import com.shm.dto.SensorDtoPi;
import com.shm.dto.SensorDtoPrint;
import com.shm.dto.SensorDtoToAdd;
import com.shm.mapper.SensorMapper;
import com.shm.model.Sensor;
import com.shm.model.SensorPin;
import com.shm.repository.SensorPinRepository;
import com.shm.repository.SensorRepository;

@Service
public class SensorService {

    private SensorRepository sensorRepository;
    private SensorPinRepository sensorPinRepository;
    private SensorMapper sensorMapper;
    private PinEventService pinEventService;

    SensorService(SensorRepository sensorRepository, SensorPinRepository sensorPinRepository,
            SensorMapper sensorMapper, PinEventService pinEventService) {
        this.sensorRepository = sensorRepository;
        this.sensorPinRepository = sensorPinRepository;
        this.sensorMapper = sensorMapper;
        this.pinEventService = pinEventService;
    }

    public void saveSensor(SensorDto sensorDto) {
        sensorRepository.save(sensorMapper.getSensorFromSensorDto(sensorDto));
        SensorPin sensorPin = sensorPinRepository.findByPinNumber(sensorDto.getPinNumber());
        sensorPin.setFree(false);
        sensorPinRepository.save(sensorPin);
    }

    public List<SensorDtoAddAlert> getAllSensor() {
        return sensorMapper.getAllSensor(sensorRepository.findAll());
    }

    public List<SensorDtoPrint> getAllActiveSensorsDto() {
        return sensorMapper.getListOfSensors(sensorRepository.findByIsActivTrue());
    }

    public List<SensorDtoPrint> getAllInActiveSensorsDto() {
        return sensorMapper.getListOfSensors(sensorRepository.findByIsActivFalse());
    }

    public List<SensorDtoPrint> getAllSensorDto() {
        return sensorMapper.getListOfSensors(sensorRepository.findAll());
    }

    public Sensor findSensor(Long id) {
        return sensorRepository.findById(id).orElse(null);
    }

    public SensorDtoPrint findSensorDtoPrint(Long id) {
        return sensorMapper.mapFromSensorToSensorDtoPrint(sensorRepository.findById(id).orElse(null));
    }

    public Sensor getSensorFromSensorDto(SensorDtoPi sensorDtoPi) {
        return sensorRepository.findBySensorPin(sensorMapper.getSenzor(sensorDtoPi).getSensorPin());
    }

    public String getNumberOfSensors() {
        return String.valueOf(sensorRepository.findAll().size());
    }

    public String getNumberOfInactiveSensors() {
        return String.valueOf(sensorRepository.findByIsActivFalse().size());
    }

    public void disableSensor(Long id) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null)
            return;
        sensor.setActiv(false);
        sensorRepository.save(sensor);
    }

    public void deleteSensor(Long id) {
        Sensor sensor = findSensor(id);
        SensorPin sensorPin = sensorPinRepository.findById(sensor.getSensorPin().getId()).orElse(null);
        sensor.setSensorPin(null);
        sensorRepository.save(sensor);
        sensorPin.setFree(true);
        sensorPinRepository.save(sensorPin);
        sensorRepository.deleteById(id);
    }

    public void enableSensor(Long id) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null)
            return;
        sensor.setActiv(true);
        sensorRepository.save(sensor);
    }

    public boolean editOnMessage(Long id, String onMessage) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null)
            return false;
        sensor.setActiveMessage(onMessage);
        sensorRepository.save(sensor);
        return true;
    }

    public boolean editOffMessage(Long id, String offMessage) {
        Sensor sensor = sensorRepository.findById(id).orElse(null);
        if (sensor == null)
            return false;
        sensor.setInactivMessage(offMessage);
        try {
            sensorRepository.save(sensor);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean addNewSensor(SensorDtoToAdd sensorDtoToAdd) {
        SensorPin sensorPin = sensorPinRepository.findById(sensorDtoToAdd.getSensorPin()).orElse(null);
        if (sensorPin == null) {
            return false;
        }

        if (sensorPin.getPinEvent() != null) {
            Long pinEventId = sensorPin.getPinEvent().getId();
            sensorPin.setPinEvent(null);
            pinEventService.deletePinEvent(pinEventId);
        }
        sensorPin.setFree(false);

        sensorPinRepository.save(sensorPin);
        return sensorRepository.save(sensorMapper.getSensorFromSensorDtoToAdd(sensorDtoToAdd, sensorPin)) != null;
    }

    public SensorDtoOverview getSensorDtoOverview(Sensor sensor) {
        return sensorMapper.getSensorDtoOverviewFromSensor(sensor);
    }

    public List<SensorDtoOverview> getAllSensorsDtoOverview() {
        return sensorMapper.getAllSensorDtoOverviews(sensorRepository.findAll());
    }

    public Sensor checkIfSensorExist(int pinNumber) {
        return sensorRepository.findBySensorPin_PinNumber(pinNumber);
    }

}
