package com.medilabo.diagnosis_risk;


import com.medilabo.diagnosis_risk.model.NoteDto;
import com.medilabo.diagnosis_risk.model.PatientDto;
import com.medilabo.diagnosis_risk.model.PatientRisk;
import com.medilabo.diagnosis_risk.repository.RiskToNoteRepository;
import com.medilabo.diagnosis_risk.repository.RiskToPatientRepository;
import com.medilabo.diagnosis_risk.service.RiskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RiskServiceTests {
//    @Autowired
    @InjectMocks
    private RiskService riskService;
//    private RiskToPatientRepository riskToPatientRepository = new RiskToPatientRepository();
//    private RiskToNoteRepository riskToNoteRepository = new RiskToNoteRepository();

    @Mock
    private RiskToPatientRepository riskToPatientRepository;

    @Mock
    private RiskToNoteRepository riskToNoteRepository;

    @BeforeEach
    public void setup() {
//        riskToPatientRepository = mock(RiskToPatientRepository.class);
//        riskToNoteRepository = mock(RiskToNoteRepository.class);
        MockitoAnnotations.openMocks(this);// initialisation
    }

    @Test
    void assessRisk_shouldReturnNone() {
        //GIVEN
        List<String> notes = new ArrayList<>();
        notes.add("no trigger term");

//        notes.add("Le patient déclare qu'il 'se sent très bien' Poids égal ou inférieur au poids recommandé");
//        notes.add("Le patient déclare qu'il ressent beaucoup de stress au travail Il se plaint également que son audition est anormale dernièrement");
//        notes.add("Le patient déclare avoir fait une réaction aux médicaments au cours des 3 derniers mois Il remarque également que son audition continue d'être anormale");
//        notes.add("Le patient déclare qu'il fume depuis peu");
//        notes.add("Le patient déclare qu'il est fumeur et qu'il a cessé de fumer l'année dernière Il se plaint également de crises d’apnée respiratoire anormales Tests de laboratoire indiquant un taux de cholestérol LDL élevé");
//        notes.add("Le patient déclare qu'il lui est devenu difficile de monter les escaliers Il se plaint également d’être essoufflé Tests de laboratoire indiquant que les anticorps sont élevés Réaction aux médicaments");
//        notes.add("Le patient déclare qu'il a mal au dos lorsqu'il reste assis pendant longtemps");
//        notes.add("Le patient déclare avoir commencé à fumer depuis peu Hémoglobine A1C supérieure au niveau recommandé");
//        notes.add("Taille, Poids, Cholestérol, Vertige et Réaction");

        PatientRisk patientTest = new PatientRisk();
        patientTest.setNotes(notes);
        patientTest.setAge(31);
        patientTest.setGender("F");

        //WHEN
        String riskLevelTest = riskService.assessRisk(patientTest);

        //THEN
        assertThat(riskLevelTest).isEqualTo("None");
    }

    @Test
    void assessRisk_shouldReturnEarlyOnset() {
        //GIVEN
        List<String> notes = new ArrayList<>();
        notes.add("vertiges, Taille");
        notes.add("Cholestérol");
        notes.add("Hémoglobine A1C");
        notes.add("ANORMAL");

        PatientRisk patientTest = new PatientRisk();
        patientTest.setNotes(notes);
        patientTest.setAge(29);
        patientTest.setGender("m");

        //WHEN
        String riskLevelTest = riskService.assessRisk(patientTest);

        //THEN
        assertThat(riskLevelTest).isEqualTo("Early onset");
    }

    @Test
    void buildPatientRisk_Ok() {
        //GIVEN
        PatientDto patientDto = new PatientDto(1L, "firstName", "LastName" , LocalDate.of(2023, 12, 31), "F", "AA", "AA" );

        when(riskToPatientRepository.getSinglePatientDto(1L)).thenReturn(patientDto);

        List<NoteDto> listNoteDtoTest = new ArrayList<>();
        NoteDto noteDtoTest =  new NoteDto("id", 1L, "term1");
        listNoteDtoTest.add(noteDtoTest);

        when(riskToNoteRepository.findNoteByCustomId(1L)).thenReturn(listNoteDtoTest);

        //WHEN
        PatientRisk patientRiskTest = riskService.buildPatientRisk(1L);
        //THEN
        assertThat(patientRiskTest.getGender()).isEqualTo("M");
        assertThat(patientRiskTest.getAge()).isEqualTo(1);
        assertThat(patientRiskTest.getNotes().size()).isEqualTo(1);
    }

}
