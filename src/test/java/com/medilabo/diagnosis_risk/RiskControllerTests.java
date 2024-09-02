package com.medilabo.diagnosis_risk;


import com.medilabo.diagnosis_risk.controller.RiskController;
import com.medilabo.diagnosis_risk.model.PatientRisk;
import com.medilabo.diagnosis_risk.service.RiskService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@WebMvcTest(RiskController.class)

public class RiskControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RiskService riskService;

    @Test
    void assessRisk_shouldReturnRiskLevel() throws Exception {
        //GIVEN
        List<String> noteListTest = new ArrayList<>();
        PatientRisk patientRiskTest = new PatientRisk(25, "M", noteListTest);
        String expectedRiskLevel = "No Risk";

        when(riskService.buildPatientRisk(1L)).thenReturn(patientRiskTest);
        when(riskService.assessRisk(any(PatientRisk.class))).thenReturn(expectedRiskLevel);


        //WHEN
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/riskService/{id}", 1L)
                )
        //THEN
                .andExpect(MockMvcResultMatchers
                        .status().isOk())
                .andExpect(content().string(expectedRiskLevel));
    }

}
