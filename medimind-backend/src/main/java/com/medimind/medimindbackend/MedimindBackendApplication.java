package com.medimind.medimindbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class MedimindBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedimindBackendApplication.class, args);
	}

}
