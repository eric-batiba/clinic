package com.btb.sante.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class CategoryResponseDto {
    private Long id;
    private String nomCategory;
    private String nomService;
}
