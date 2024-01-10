package com.btb.sante.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ServiceDto  {
    @NotBlank(message = "le nom du service est obligatoire")
    private String nomService;
}
