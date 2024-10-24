package com.medilabo.diagnosis_risk.repository;

import com.medilabo.diagnosis_risk.configuration.FeignClientConfig;
import com.medilabo.diagnosis_risk.model.NoteDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Repository
@FeignClient(name = "ToDiagnosisNote",  url = "#{toNoteServiceUrl}", configuration = FeignClientConfig.class)
//@FeignClient(name = "ToDiagnosisNote", url = "http://localhost:8083", configuration = FeignClientConfig.class)
public interface RiskToNoteRepository {
    @RequestMapping(method = RequestMethod.GET, value = "/notes/{id}")
    List<NoteDto> findNoteByCustomId(@PathVariable("id") Long id);

}