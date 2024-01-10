package com.btb.sante.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@EqualsAndHashCode
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsultationId implements Serializable {
    @Column(name = "patient_id",nullable = false)
    private Long patientId;
    @Column(name = "examen_id",nullable = false)
    private Long examenId;
}
