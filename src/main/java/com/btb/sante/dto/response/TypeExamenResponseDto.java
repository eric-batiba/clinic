package com.btb.sante.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TypeExamenResponseDto {

    private Long id;
    private String nom;
    private CategoryResponseDto categoryResponseDto;
}
