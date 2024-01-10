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
@ToString(exclude = {"typeExamens","examens"})
@Table(name = "category")
@DynamicInsert
@DynamicUpdate
public class Category extends AbstractEntity{
    @SequenceGenerator(name = "category_sequence",sequenceName = "category_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "category_sequence")
    private Long id;

    @Column(name = "nom",nullable = false, unique = true)
    private String nom;

    @OneToMany(mappedBy = "category")
    List<TypeExamen> typeExamens;

    @OneToMany(mappedBy = "category")
    List<Examen> examens;

    @ManyToOne
    @JoinColumn(name = "service_id",
    foreignKey = @ForeignKey(name = "category_service_id_fk"))
    private Service service;
}
