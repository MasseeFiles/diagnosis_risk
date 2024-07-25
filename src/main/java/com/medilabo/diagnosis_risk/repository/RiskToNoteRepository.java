package com.medilabo.diagnosis_risk.repository;

import com.medilabo.diagnosis_risk.model.NoteDto;
import com.medilabo.diagnosis_risk.model.PatientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Repository
@FeignClient(name = "diagnosisNote", url = "http://localhost:8083")
public interface RiskToNoteRepository {
        @RequestMapping(method = RequestMethod.GET, value = "/note/{id}")
        List<NoteDto> findNoteByCustomId(@PathVariable("id") Long id);

}
