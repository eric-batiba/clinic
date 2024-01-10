package com.btb.sante.controller.api;

import com.btb.sante.dto.request.ExamenRequestDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ExamenNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IexamenController {
    @PostMapping
    ResponseEntity<ExamenResponseDto> save(@RequestBody @Valid ExamenRequestDto examenRequestDto) throws CategoryNotFoundException;
   @GetMapping
    ResponseEntity<List<ExamenResponseDto>> getAll();
    @GetMapping("/{id}")
    ResponseEntity<Optional<ExamenResponseDto>> getOne(Long id) throws ExamenNotFoundException;
    @PutMapping("/{id}/update")
    ResponseEntity<ExamenResponseDto> update(ExamenRequestDto examenRequestDto, Long id) throws ExamenNotFoundException, CategoryNotFoundException;
    @DeleteMapping("/{id}/delete")
    ResponseEntity<Void> delete(Long id) throws ExamenNotFoundException;
}
