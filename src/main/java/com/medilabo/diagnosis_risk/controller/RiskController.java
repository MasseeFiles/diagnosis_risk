package com.medilabo.diagnosis_risk.controller;

import com.medilabo.diagnosis_risk.model.PatientRisk;
import com.medilabo.diagnosis_risk.service.RiskService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RiskController {

    private static final Logger logger = LogManager.getLogger("RiskController");

    @Autowired
    private RiskService riskService;

    @GetMapping("/risk/{id}")
    public String assessRisk(@PathVariable Long id) {

        logger.info("Requete pour obtenir l'evaluation du risque diabetique pour un patient");

        PatientRisk patientRisk = riskService.buildPatientRisk(id);
        String riskLevel = riskService.assessRisk(patientRisk);

        return riskLevel;
    }
}
