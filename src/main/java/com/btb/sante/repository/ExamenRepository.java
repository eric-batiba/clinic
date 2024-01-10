package com.btb.sante.repository;

import com.btb.sante.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExamenRepository extends JpaRepository<Examen, Long> {
    Optional<Examen> findByNom(String nom);

    Boolean existsExamenByNom(String nomExam);
}