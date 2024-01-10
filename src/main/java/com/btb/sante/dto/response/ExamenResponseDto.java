package com.btb.sante.dto.response;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExamenResponseDto {
    private Long id;
    private String nom;
    private BigDecimal prix;
    private Double pourcentage;
    private CategoryResponseDto categoryResponseDto;

}
