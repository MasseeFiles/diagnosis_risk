package com.medilabo.diagnosis_risk.repository;

import com.medilabo.diagnosis_risk.model.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@Repository
//public interface RiskToPatientRepository {
////    @RequestMapping(method = RequestMethod.GET, value = "/patient/{id}")
//    @GetMapping(value = "/patient/{id}")
//    PatientDto getSinglePatientDto(@PathVariable("id") Long id);
//
//}

@Repository
//@FeignClient(name = "diagnosisPatient", url = "http://localhost:8084")
@FeignClient(name = "diagnosisPatient", url = "http://localhost:8081")

public interface RiskToPatientRepository {
    @GetMapping(value = "/patient/{id}")
    PatientDto getSinglePatientDto(@PathVariable("id") Long id);

}