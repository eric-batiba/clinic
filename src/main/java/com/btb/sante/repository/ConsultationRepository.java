package com.btb.sante.repository;

import com.btb.sante.entity.Consultation;
import com.btb.sante.entity.ConsultationId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultationRepository extends JpaRepository<Consultation, ConsultationId> {
}
