package com.btb.sante.dto;

import com.btb.sante.entity.Centre;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class MedecinDto {

    private Long id;
    private String nom;
    private String email;

    private Centre centre;
}
