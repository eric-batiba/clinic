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
@ToString(exclude = {"categories"})
@Table(name = "service")
@DynamicInsert
@DynamicUpdate
public class Service extends AbstractEntity{
    @SequenceGenerator(name = "service_sequence", sequenceName = "service_sequence")
    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "service_sequence")
    @Column(name = "id",nullable = false,updatable = false)
    private Long id;
    @Column(name = "nom",nullable = false)
    private String nom;

    @OneToMany(mappedBy = "service")
    List<Category> categories;
}
