package com.btb.sante.service;

import com.btb.sante.dto.request.CategoryRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface Icategory {
    CategoryResponseDto saveCat(CategoryRequestDto categoryRequestDto) throws ServiceNotFoundException, ServiceExistException;

    List<CategoryResponseDto> findAll();

    Optional<CategoryResponseDto> findById(Long id) throws CategoryNotFoundException;

    CategoryResponseDto update(CategoryRequestDto categoryRequestDto, Long id) throws CategoryNotFoundException, ServiceNotFoundException;

    void updateOther(CategoryRequestDto categoryRequestDto, Long id) throws CategoryNotFoundException;

    void delete(Long id) throws CategoryNotFoundException;
}
