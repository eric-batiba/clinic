package com.btb.sante.controller.api;

import com.btb.sante.dto.request.CategoryRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
public interface IcategoryController {

    @PostMapping
    ResponseEntity<CategoryResponseDto> save(@RequestBody @Valid CategoryRequestDto categoryRequestDto) throws ServiceNotFoundException, ServiceExistException;

    @GetMapping
    ResponseEntity<List<CategoryResponseDto>> getAll();

    @GetMapping("/{id}")
    ResponseEntity<Optional<CategoryResponseDto>> getById(@PathVariable Long id) throws CategoryNotFoundException;

    @PutMapping("/{id}/update2")
    ResponseEntity<String> updateOther(@RequestBody @Valid CategoryRequestDto categoryRequestDto, @PathVariable Long id) throws CategoryNotFoundException, ServiceNotFoundException;

    @PutMapping("/{id}")
    ResponseEntity<CategoryResponseDto> update(@RequestBody @Valid CategoryRequestDto categoryRequestDto, @PathVariable Long id) throws CategoryNotFoundException, ServiceNotFoundException;
    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id) throws CategoryNotFoundException;


}
