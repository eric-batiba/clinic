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
@Table(name = "medecin",
        uniqueConstraints = {
        @UniqueConstraint(name = "medecin_email_unique",columnNames = "email")
        })
public class Medecin extends AbstractEntity{
    @SequenceGenerator(name = "medecin_sequence", sequenceName = "medecin_sequence",allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "medecin_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;
    @Column(name = "nom",nullable = false)
    private String nom;
    @Column(name = "email",nullable = false)
    private String email;

    @ManyToOne
    @JoinColumn(name = "centre_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "medecin_centre_id_fk"))
    private Centre centre;
}
