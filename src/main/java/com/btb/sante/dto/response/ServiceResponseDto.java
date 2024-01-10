package com.btb.sante.dto.response;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class ServiceResponseDto {
    private Long id;
    private String nomService;
}
