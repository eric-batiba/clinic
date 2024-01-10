package com.btb.sante.dto;

import com.btb.sante.entity.Category;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TypeExamenDto{

    private Long id;
    private String nom;

    private Category category;
}
