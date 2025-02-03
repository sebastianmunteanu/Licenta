package com.shm.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ConnectionService {
  private final String raspberryEndpoint = new String("http://192.168.1.10:5555/test-api");

  public boolean checkRasperryConn() {
    RestTemplate restTemplate = new RestTemplate();
    try {
      ResponseEntity<String> response = restTemplate.getForEntity(raspberryEndpoint, String.class);
      return response.getStatusCode() == HttpStatus.OK;
    } catch (Exception e) {
      return false;
    }
  }
}
