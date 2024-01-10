package com.btb.sante.dto;

import com.btb.sante.entity.Address;
import com.btb.sante.entity.Consultation;
import jakarta.persistence.Embedded;
import lombok.*;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatientDto {

    private Long id;
    private String nom;
    private String prenom;
    private int telephone;
    private String email;
    private Address address;

    List<Consultation> consultations;
}
