package com.btb.sante.service.impl;

import com.btb.sante.dto.request.ExamenRequestDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.entity.Category;
import com.btb.sante.entity.Examen;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ExamenNotFoundException;
import com.btb.sante.mapper.ExamenMapper;
import com.btb.sante.repository.CategoryRepository;
import com.btb.sante.repository.ExamenRepository;
import com.btb.sante.service.Iexamen;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.lang.String.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExamenService implements Iexamen {
    public static final String EXAMEN_SERVICE = "ExamenService";
    private final ExamenRepository examenRepository;
    private final CategoryRepository categoryRepository;
    private final ExamenMapper mapper;
    @Override
    public ExamenResponseDto saveExamen(ExamenRequestDto examenRequestDto) throws CategoryNotFoundException {
        log.info(EXAMEN_SERVICE + ": saveExamen()");
        Category category = categoryRepository.findByNom(examenRequestDto.getNomCategory()).orElseThrow(() -> new CategoryNotFoundException(format("Category with nom %s not found", examenRequestDto.getNomCategory())));
        Examen examen = mapper.toEntity(examenRequestDto);
        examen.setCategory(category);
        return mapper.toDto(examenRepository.save(examen));
    }

    @Override
    public List<ExamenResponseDto> findAll() {
        log.info(EXAMEN_SERVICE + ": findAll()");
        return examenRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<ExamenResponseDto> findById(Long id) throws ExamenNotFoundException {
        log.info(EXAMEN_SERVICE + " with {} : findById()", id);
        return Optional.ofNullable(mapper.toDto(getExamen(id)));
    }

    @Override
    public ExamenResponseDto update(ExamenRequestDto examenRequestDto, Long id) throws ExamenNotFoundException, CategoryNotFoundException {
        log.info(EXAMEN_SERVICE + " with {} : update()", id);
        getExamen(id);
        Category category = getCategoryByNom(examenRequestDto);
        Examen examen = mapper.toEntity(examenRequestDto);
        examen.setCategory(category);
        return mapper.toDto(examenRepository.save(examen));
    }

    @Override
    public void updateOther(ExamenRequestDto examenRequestDto, Long id) throws ExamenNotFoundException {
        log.info(EXAMEN_SERVICE + " with {} : update()", id);
        Examen examen = getExamen(id);
        mapper.update(examenRequestDto,examen);
    }

    @Override
    public void delete(Long id) throws ExamenNotFoundException {
        log.info(EXAMEN_SERVICE + " with {} : delete()", id);
        getExamen(id);
        examenRepository.deleteById(id);
    }

    private Examen getExamen(Long id) throws ExamenNotFoundException {
        return examenRepository.findById(id).orElseThrow(() -> new ExamenNotFoundException(format("examen with id %s not found", id)));
    }

    private Category getCategoryByNom(ExamenRequestDto examenRequestDto) throws CategoryNotFoundException {
        return categoryRepository.findByNom(examenRequestDto.getNomCategory()).orElseThrow(() -> new CategoryNotFoundException(format("category with %s not found", examenRequestDto.getNomCategory())));
    }
}
