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
@Table(name = "type_exam")
public class TypeExamen extends AbstractEntity{
    @SequenceGenerator(name = "type_exam_sequence", sequenceName = "type_exam_sequence")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "type_exam_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;
    @Column(name = "nom",nullable = false)
    private String nom;

    @ManyToOne
    @JoinColumn(name = "category_id",referencedColumnName = "id",
    foreignKey = @ForeignKey(name = "type_exam_category_id_fk"))
    private Category category;
}
