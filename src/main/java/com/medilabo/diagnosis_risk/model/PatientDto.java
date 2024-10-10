package com.medilabo.diagnosis_risk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientDto {
    private Long patientId;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String gender;

    private String address;

    private String phoneNumber;

}
