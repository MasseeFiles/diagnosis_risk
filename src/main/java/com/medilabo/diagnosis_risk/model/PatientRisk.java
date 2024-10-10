package com.medilabo.diagnosis_risk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientRisk {
    private int age;
    private String gender;
    private List<String> notes;

}
