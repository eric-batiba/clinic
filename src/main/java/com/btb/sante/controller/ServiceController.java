package com.btb.sante.controller;

import com.btb.sante.controller.api.IserviceController;
import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;
import com.btb.sante.service.impl.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/services")
@RequiredArgsConstructor
public class ServiceController implements IserviceController {
    private final ServiceImpl service;
    @Override
    public ResponseEntity<ServiceResponseDto> save(ServiceRequestDto serviceRequestDto) throws ServiceExistException {
        return ResponseEntity.status(CREATED).body(service.save(serviceRequestDto));
    }

    @Override
    public ResponseEntity<Optional<ServiceResponseDto>> findById(Long id) throws ServiceNotFoundException {
        return ResponseEntity.ok(service.findById(id));
    }

    @Override
    public ResponseEntity<List<ServiceResponseDto>> findAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @Override
    public ResponseEntity<Void> delete(Long id) throws ServiceNotFoundException {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<String> update(ServiceRequestDto serviceRequestDto, Long id) throws ServiceNotFoundException {
         service.update(serviceRequestDto,id);
         return ResponseEntity.ok("Update ok");
    }

    @Override
    public ResponseEntity<ServiceResponseDto> updateOther(ServiceRequestDto serviceRequestDto, Long id) throws ServiceNotFoundException {
        return ResponseEntity.ok(service.updateOther(serviceRequestDto,id));
    }
}
