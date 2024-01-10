package com.btb.sante.entity;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Embeddable
public class Address implements Serializable {
    private String address1;
    private String address2;
    private String pays;
    private String ville;
    private String zipCode  ;
}
