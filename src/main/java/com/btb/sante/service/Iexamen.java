package com.btb.sante.service;

import com.btb.sante.dto.request.ExamenRequestDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ExamenNotFoundException;

import java.util.List;
import java.util.Optional;

public interface Iexamen {
    ExamenResponseDto saveExamen(ExamenRequestDto examenRequestDto) throws ExamenNotFoundException, CategoryNotFoundException;

    List<ExamenResponseDto> findAll();

    Optional<ExamenResponseDto> findById(Long id) throws ExamenNotFoundException;

    ExamenResponseDto update(ExamenRequestDto examenRequestDto, Long id) throws ExamenNotFoundException, CategoryNotFoundException;

    void updateOther(ExamenRequestDto examenRequestDto, Long id) throws ExamenNotFoundException;

    void delete(Long id) throws ExamenNotFoundException;
}
