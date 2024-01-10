package com.btb.sante.entity;

import jakarta.persistence.*;
import lombok.*;

import static jakarta.persistence.GenerationType.SEQUENCE;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "notification")
public class Notification {
    @SequenceGenerator(name = "notification_sequence", sequenceName = "notification_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "notification_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;

    @ManyToOne
    private Consultation consultation;
}
