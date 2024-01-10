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
@Table(name = "entreprise",
        uniqueConstraints = {
                @UniqueConstraint(name = "entreprise_email_unique", columnNames = "email")
        })
public class Entreprise extends AbstractEntity {
    @SequenceGenerator(name = "entreprise_sequence", sequenceName = "entreprise_sequence",allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "entreprise_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;
    @Column(name = "nom", nullable = false)
    private String nom;
    @Column(name = "email", nullable = false)
    private String email;
    @Embedded
    private Address address;

}
