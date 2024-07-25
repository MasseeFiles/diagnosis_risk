package com.medilabo.diagnosis_risk.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NoteDto {
        private String id;
        private Long customId;
        private String noteField;

}
