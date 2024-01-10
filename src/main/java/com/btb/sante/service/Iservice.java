package com.btb.sante.service;

import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;

import java.util.List;
import java.util.Optional;

public interface Iservice {

    ServiceResponseDto  save(ServiceRequestDto serviceRequestDto) throws ServiceNotFoundException, ServiceExistException;
    List<ServiceResponseDto> findAll();
    Optional<ServiceResponseDto> findById(Long id) throws ServiceNotFoundException;
    void update(ServiceRequestDto serviceRequestDto, Long id) throws ServiceNotFoundException;

    ServiceResponseDto updateOther(ServiceRequestDto serviceRequestDto, Long id) throws ServiceNotFoundException;

    void delete(Long id) throws ServiceNotFoundException;
}
