package com.jason;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.turbine.EnableTurbine;

@SpringBootApplication
@EnableTurbine
public class TrubineServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrubineServiceApplication.class, args);
	}
}
