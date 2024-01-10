package com.btb.sante.dto.request;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class TypeExamenRequestDto {

    private String nom;
    private String nomCategory;
}
