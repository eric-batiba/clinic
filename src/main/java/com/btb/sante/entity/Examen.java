package com.btb.sante.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.math.BigDecimal;
import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "examen")
@DynamicInsert
@DynamicUpdate
public class Examen extends AbstractEntity{
    @SequenceGenerator(name = "examen_sequence", sequenceName = "examen_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "examen_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;
    @Column(name = "nom",nullable = false)
    private String nom;
    @Column(name = "prix",nullable = false)
    private BigDecimal prix;
    @Column(name = "pourcentage",nullable = false)
    private Double pourcentage;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "examen_category_id_fk"))
    private Category category;

    @OneToMany(mappedBy = "examen")
    List<Consultation> consultations;
}
