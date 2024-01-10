package com.btb.sante.dto;

import com.btb.sante.entity.Consultation;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class NotificationDto {
    private Long id;

    private Consultation consultation;
}
