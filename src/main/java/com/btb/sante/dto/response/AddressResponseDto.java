package com.btb.sante.dto.response;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
@Embeddable
public class AddressResponseDto {
    private String address1;
    private String address2;
    private String pays;
    private String ville;
    private String zipCode  ;
}
