package com.medilabo.diagnosis_risk.repository;

import com.medilabo.diagnosis_risk.configuration.FeignClientConfig;
import com.medilabo.diagnosis_risk.model.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Repository
@FeignClient(name = "diagnosisPatient", url = "http://localhost:8084")
//@FeignClient(name = "diagnosisPatient", url = "http://localhost:8084", configuration = FeignClientConfig.class)

public interface RiskToPatientRepository {
    @GetMapping(value = "/patientService/{id}")
    PatientDto getSinglePatientDto(@PathVariable("id") Long id);

}