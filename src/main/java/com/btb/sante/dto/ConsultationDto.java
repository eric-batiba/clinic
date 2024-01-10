package com.btb.sante.dto;

import com.btb.sante.entity.Examen;
import com.btb.sante.entity.Patient;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ConsultationDto {

    private Patient patient;

    private Examen examen;

    private String observation;
}
