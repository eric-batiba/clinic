package com.btb.sante.controller;

import com.btb.sante.controller.api.IpatientController;
import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ExamenNotFoundException;
import com.btb.sante.service.impl.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientController implements IpatientController {
    private final PatientService patientService;
    @Override
    public ResponseEntity<PatientResponseDto> save(PatientRequestDto patientRequestDto) throws ExamenNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(patientService.savePatient(patientRequestDto));
    }

    @Override
    public ResponseEntity<List<PatientResponseDto>> getAll() {
        return ResponseEntity.ok(patientService.findAll());
    }

    @Override
    public ResponseEntity<Optional<PatientResponseDto>> getOne(Long id) {
        return ResponseEntity.ok(patientService.findById(id));
    }

    @Override
    public ResponseEntity<PatientResponseDto> update(PatientRequestDto patientRequestDto, Long id) throws ExamenNotFoundException {
        return ResponseEntity.ok(patientService.update(patientRequestDto,id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        patientService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
