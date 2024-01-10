package com.btb.sante.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "consultation")
public class Consultation {
    @EmbeddedId
    private ConsultationId id;

    @ManyToOne
    @JoinColumn(name = "patient_id",foreignKey = @ForeignKey(name = "patient_id_fk"))
    @MapsId("patientId")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "examen_id",foreignKey = @ForeignKey(name = "examen_id_fk"))
    @MapsId("examenId")
    private Examen examen;

    @Column(name = "observation")
    private String observation;
}
