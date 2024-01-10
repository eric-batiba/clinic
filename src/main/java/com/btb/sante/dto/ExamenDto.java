package com.btb.sante.dto;

import com.btb.sante.entity.Category;
import com.btb.sante.entity.Consultation;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ExamenDto {
    private Long id;
    private String nom;
    private BigDecimal prix;
    private Double pourcentage;

    private Category category;

    List<Consultation> consultations;
}
