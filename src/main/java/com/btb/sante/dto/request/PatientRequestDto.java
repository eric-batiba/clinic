package com.btb.sante.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatientRequestDto {
    @NotBlank(message = "ce champ est obligatoire")
    private String nom;
    private String prenom;
    private int telephone;
    private String email;
    private AddressRequestDto addressRequestDto;
    private String nomExam;

}
