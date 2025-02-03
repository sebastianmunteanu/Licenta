package com.shm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ShmApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShmApplication.class, args);
	}
}
