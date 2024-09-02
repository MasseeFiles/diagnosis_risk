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
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RiskServiceTests {
    @InjectMocks
    private RiskService riskService;

    @Mock
    private RiskToPatientRepository riskToPatientRepository;

    @Mock
    private RiskToNoteRepository riskToNoteRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void assessRisk_shouldReturnNone() {
        //GIVEN
        List<String> notes = new ArrayList<>();
        notes.add("no trigger term");

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
        PatientDto patientDto = new PatientDto(1L, "firstName", "LastName", LocalDate.of(2023, 12, 31), "F", "AA", "AA");

        when(riskToPatientRepository.getSinglePatientDto(1L)).thenReturn(patientDto);

        List<NoteDto> listNoteDtoTest = new ArrayList<>();
        NoteDto noteDtoTest = new NoteDto("id", 1L, "term1");
        listNoteDtoTest.add(noteDtoTest);

        when(riskToNoteRepository.findNoteByCustomId(1L)).thenReturn(listNoteDtoTest);

        //WHEN
        PatientRisk patientRiskTest = riskService.buildPatientRisk(1L);
        //THEN
        assertThat(patientRiskTest.getGender()).isEqualTo("F");
        assertThat(patientRiskTest.getNotes().size()).isEqualTo(1);
    }

}
