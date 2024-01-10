package com.btb.sante.controller.api;

import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

public interface IserviceController {
    @PostMapping
    ResponseEntity<ServiceResponseDto> save(@RequestBody @Valid ServiceRequestDto serviceRequestDto) throws ServiceExistException;
    @GetMapping("/{id}")
    ResponseEntity<Optional<ServiceResponseDto>> findById(@PathVariable Long id) throws ServiceNotFoundException;

    @GetMapping
    ResponseEntity<List<ServiceResponseDto>> findAll();

    @DeleteMapping("/{id}/delete")
    ResponseEntity<Void> delete(@PathVariable Long id) throws ServiceNotFoundException;
    @PutMapping("/{id}/update")
    ResponseEntity<String> update(@RequestBody @Valid ServiceRequestDto serviceRequestDto, @PathVariable Long id) throws ServiceNotFoundException;

  @PutMapping("/{id}/updateOther")
    ResponseEntity<ServiceResponseDto> updateOther(@RequestBody @Valid ServiceRequestDto serviceRequestDto, @PathVariable Long id) throws ServiceNotFoundException;


}
