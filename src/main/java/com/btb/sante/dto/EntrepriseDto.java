package com.btb.sante.dto;

import com.btb.sante.entity.Address;
import jakarta.persistence.Embedded;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class EntrepriseDto{

    private Long id;
    private String nom;
    private String email;
    @Embedded
    private Address address;

}
