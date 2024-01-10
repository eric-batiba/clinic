package com.btb.sante.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "patient",
    uniqueConstraints = {
        @UniqueConstraint(name = "patient_email_unique",columnNames = "email")
})
@DynamicInsert
@DynamicUpdate
public class Patient extends AbstractEntity{
    @SequenceGenerator(name = "patient_sequence", sequenceName = "patient_sequence")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "patient_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;
    @Column(name = "nom",nullable = false,columnDefinition = "TEXT")
    private String nom;
    @Column(name = "prenom",nullable = false)
    private String prenom;
    @Column(name = "telephone",nullable = false)
    private int telephone;
    @Column(name = "email",nullable = false)
    private String email;
    @Embedded
    private Address address;

    @ManyToOne
    @JoinColumn(name = "examen_id")
    private Examen examen;

    @OneToMany(mappedBy = "patient")
    List<Consultation> consultations;
}
