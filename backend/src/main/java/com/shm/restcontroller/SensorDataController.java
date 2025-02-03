package com.shm.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.dto.AnalogueSensorPi;
import com.shm.dto.DigitalSensorPi;
import com.shm.model.Sensor;
import com.shm.service.PinEventService;
import com.shm.service.RaspberryPinService;
import com.shm.service.SensorDataService;
import com.shm.service.SensorService;

@RestController
@RequestMapping("api")
public class SensorDataController {

    private final SensorDataService sensorDataService;
    private final SensorService sensorService;
    private final RaspberryPinService raspberryPinService;
    private final PinEventService pinEventService;

    public SensorDataController(SensorDataService sensorDataService, SensorService sensorService,
            RaspberryPinService raspberryPinService, PinEventService pinEventService) {
        this.sensorDataService = sensorDataService;
        this.sensorService = sensorService;
        this.raspberryPinService = raspberryPinService;
        this.pinEventService = pinEventService;
    }

    @PostMapping("/digital-sensors")
    public ResponseEntity<String> receiveDigitalSensorData(@RequestBody DigitalSensorPi digitalSensor) {
        Sensor sensor = sensorService.checkIfSensorExist(digitalSensor.getSensorPin());
        if (sensor == null) {
            pinEventService.savePinEvent(digitalSensor.getSensorPin());
            raspberryPinService.saveRasperryPiEvent(digitalSensor);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        sensorDataService.processRecivedAlert(sensor, digitalSensor.isSensorState());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/analogue-sensors")
    public ResponseEntity<String> receiveAnalogueSensorData(@RequestBody AnalogueSensorPi analogueSensor) {
        Sensor sensor = sensorService.checkIfSensorExist(analogueSensor.getSensorPin());
        if (sensor == null) {
            pinEventService.savePinEvent(analogueSensor.getSensorPin());
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sensorDataService.processRecivedAlert(sensor, analogueSensor.isSensorState());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/sensor/alert/{sensorId}")
    public ResponseEntity<String> generateSMS(@PathVariable Long sensorId) {
        Sensor sensor = sensorService.findSensor(sensorId);
        if (sensor == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        sensorDataService.processRecivedAlert(sensor, true);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
