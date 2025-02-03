package com.shm.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shm.service.ConnectionService;

@RestController
public class ConectivityR {

  private ConnectionService connectionService;

  ConectivityR(ConnectionService connectionService) {
    this.connectionService = connectionService;
  }

  @GetMapping("/check-conectivity")
  public ResponseEntity<Boolean> checkConectivity() {
    return ResponseEntity.ok(true);
  }

  @GetMapping("/check-raspberry-con")
  public ResponseEntity<Boolean> checkRaspberryConnect() {
    boolean isConnected = connectionService.checkRasperryConn();
    return isConnected ? ResponseEntity.ok(true) : ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(false);
  }
}
