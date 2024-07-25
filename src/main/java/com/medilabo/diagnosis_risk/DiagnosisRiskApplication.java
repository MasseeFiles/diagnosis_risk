package com.medilabo.diagnosis_risk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class DiagnosisRiskApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiagnosisRiskApplication.class, args);
	}

}
