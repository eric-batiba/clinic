package com.btb.sante.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

import static jakarta.persistence.GenerationType.SEQUENCE;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "centre")
public class Centre extends AbstractEntity{
    @SequenceGenerator(name = "centre_sequence",sequenceName = "centre_sequence", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = SEQUENCE,generator = "centre_sequence")
    private Long id;
    private String nom;

    @OneToMany(mappedBy = "centre")
    List<Medecin> medecins ;
}
