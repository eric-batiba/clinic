package com.btb.sante.repository;

import com.btb.sante.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ServiceRepository extends JpaRepository<Service, Long> {
    Optional<Service> findByNom(String nom);
    Boolean existsServiceByNom(String nom);


}
