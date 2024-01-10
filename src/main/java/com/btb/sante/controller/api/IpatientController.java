package com.btb.sante.controller.api;

import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.exception.ExamenNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IpatientController {
    @PostMapping
    ResponseEntity<PatientResponseDto> save(@RequestBody @Valid PatientRequestDto patientRequestDto) throws ExamenNotFoundException;
   @GetMapping
    ResponseEntity<List<PatientResponseDto>> getAll();
    @GetMapping("/{id}")
    ResponseEntity<Optional<PatientResponseDto>> getOne(Long id) throws ExamenNotFoundException;
    @PutMapping("/{id}/update")
    ResponseEntity<PatientResponseDto> update(PatientRequestDto patientRequestDto, Long id) throws ExamenNotFoundException;
    @DeleteMapping("/{id}/delete")
    ResponseEntity<Void> delete(Long id);
}
