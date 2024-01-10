package com.btb.sante.dto;

import com.btb.sante.entity.Medecin;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class CentreDto{

    private Long id;
    private String nom;

    List<Medecin> medecins ;
}
