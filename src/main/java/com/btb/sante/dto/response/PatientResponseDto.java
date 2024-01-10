package com.btb.sante.dto.response;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class PatientResponseDto {
    private String nom;
    private String prenom;
    private int telephone;
    private String email;
    private AddressResponseDto addressResponseDto;
    private ExamenResponseDto examenResponseDto;

}
