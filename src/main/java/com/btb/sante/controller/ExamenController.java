package com.btb.sante.controller;

import com.btb.sante.controller.api.IexamenController;
import com.btb.sante.dto.request.ExamenRequestDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.exception.CategoryNotFoundException;
import com.btb.sante.exception.ExamenNotFoundException;
import com.btb.sante.service.impl.ExamenService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping(value = "/examens")
@RequiredArgsConstructor
public class ExamenController implements IexamenController {
    private final ExamenService examenService;
    @Override
    public ResponseEntity<ExamenResponseDto> save(ExamenRequestDto examenRequestDto) throws CategoryNotFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(examenService.saveExamen(examenRequestDto));
    }

    @Override
    public ResponseEntity<List<ExamenResponseDto>> getAll() {
        return ResponseEntity.ok(examenService.findAll());
    }

    @Override
    public ResponseEntity<Optional<ExamenResponseDto>> getOne(Long id) throws ExamenNotFoundException {
        return ResponseEntity.ok(examenService.findById(id));
    }

    @Override
    public ResponseEntity<ExamenResponseDto> update(ExamenRequestDto examenRequestDto, Long id) throws ExamenNotFoundException, CategoryNotFoundException {
        return ResponseEntity.ok(examenService.update(examenRequestDto,id));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws ExamenNotFoundException {
        examenService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
