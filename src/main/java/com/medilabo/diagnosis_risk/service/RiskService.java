package com.medilabo.diagnosis_risk.service;

import com.medilabo.diagnosis_risk.model.NoteDto;
import com.medilabo.diagnosis_risk.model.PatientDto;
import com.medilabo.diagnosis_risk.model.PatientRisk;
import com.medilabo.diagnosis_risk.repository.RiskToNoteRepository;
import com.medilabo.diagnosis_risk.repository.RiskToPatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class RiskService {
    @Autowired
    RiskToPatientRepository riskToPatientRepository;
    @Autowired
    RiskToNoteRepository riskToNoteRepository;
    private List<String> triggers = Arrays.asList(
            "Hémoglobine A1C",
            "Microalbumine",
            "Taille",
            "Poids",
            "Fumeur",
            "Fumeuse",
            "Anormal",
            "Cholestérol",
            "Vertiges",
            "Rechute",
            "Réaction",
            "Anticorps"
    );

    public String assessRisk(PatientRisk patientRisk) {

        int triggerTermCount = countTriggerTerms(patientRisk.getNotes());
        int age = patientRisk.getAge();

        String gender = patientRisk.getGender().toUpperCase();  ////conversion en uppercase pour eviter pb de majuscules et minuscules

        String riskLevel = "None";

        if (triggerTermCount >= 2 && triggerTermCount <= 5 && age > 30) {
            riskLevel = "Borderline";
        } else if (gender.equals("M") && age < 30 && triggerTermCount >= 3 && triggerTermCount < 5) {
            riskLevel = "In danger";
        } else if (gender.equals("F") && age < 30 && triggerTermCount >= 4 && triggerTermCount < 7) {
            riskLevel = "In danger";
        } else if (age >= 30 && (triggerTermCount >= 6 && triggerTermCount <= 7)) {
            riskLevel = "In danger";
        } else if (gender.equals("M") && age < 30 && triggerTermCount >= 5) {
            riskLevel = "Early onset";
        } else if (gender.equals("F") && age < 30 && triggerTermCount >= 7) {
            riskLevel = "Early onset";
        } else if (age >= 30 && triggerTermCount >= 8) {
            riskLevel = "Early onset";
        }
        return riskLevel;
    }

    private int countTriggerTerms(List<String> notes) {
        int count = 0;
        for (String note : notes) {
            for (String trigger : triggers) {
                if (note.toLowerCase().contains(trigger.toLowerCase())) {       //conversion en lowercase pour eviter pb de majuscules et minuscules
                    count++;
                }
            }
        }
        return count;
    }

    public PatientRisk buildPatientRisk(Long id) {
        PatientDto patientDto = riskToPatientRepository.getSinglePatientDto(id);

        PatientRisk patientRisk = new PatientRisk();
        int patientAge = calculateAge(patientDto.getDateOfBirth());
        patientRisk.setAge(patientAge);

        String gender = patientDto.getGender();
        patientRisk.setGender(gender);

        List<NoteDto> listNoteDto = riskToNoteRepository.findNoteByCustomId(id);
        List<String> listString = new ArrayList<>();
        for (NoteDto noteDto : listNoteDto) {
            listString.add(noteDto.getNoteField());
        }
        patientRisk.setNotes(listString);

        return patientRisk;
    }

    private int calculateAge(LocalDate dateOfBirth) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(dateOfBirth, currentDate).getYears();
    }

}
