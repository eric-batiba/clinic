package com.btb.sante.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CategoryRequestDto {
    @NotBlank(message = "le nom category est obligatoire")
    private String nomCategory;
    private String nomService;
}
