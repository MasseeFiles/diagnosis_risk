package com.medilabo.diagnosis_risk.repository;

import com.medilabo.diagnosis_risk.configuration.FeignClientConfig;
import com.medilabo.diagnosis_risk.model.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@Repository
@FeignClient(name = "toDiagnosisPatient", url = "#{toPatientServiceUrl}", configuration = FeignClientConfig.class)
//@FeignClient(name = "toDiagnosisPatient", url = "http://localhost:8081", configuration = FeignClientConfig.class)
public interface RiskToPatientRepository {
    @GetMapping(value = "/patients/{id}")
    PatientDto getSinglePatientDto(@PathVariable("id") Long id);

}