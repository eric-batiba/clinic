package com.btb.sante.service;

import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.exception.ExamenNotFoundException;

import java.util.List;
import java.util.Optional;

public interface Ipatient {
    PatientResponseDto savePatient(PatientRequestDto patientRequestDto) throws ExamenNotFoundException;

    List<PatientResponseDto> findAll();

    Optional<PatientResponseDto> findById(Long id);

    PatientResponseDto update(PatientRequestDto patientRequestDto, Long id) throws ExamenNotFoundException;

    void delete(Long id);
}
