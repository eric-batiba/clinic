package com.btb.sante.dto.request;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExamenRequestDto {
    private Long id;
    private String nom;
    private BigDecimal prix;
    private Double pourcentage;
    private String nomCategory;

}
