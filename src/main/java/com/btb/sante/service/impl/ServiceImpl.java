package com.btb.sante.service.impl;

import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.entity.Service;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;
import com.btb.sante.mapper.ServiceMapper;
import com.btb.sante.repository.ServiceRepository;
import com.btb.sante.service.Iservice;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class ServiceImpl implements Iservice {
    public static final String SERVICE_SERVICE = "ServiceService";
    private final ServiceRepository serviceRepository;
    private final ServiceMapper mapper;

    @Override
    public ServiceResponseDto save(ServiceRequestDto serviceRequestDto) throws ServiceExistException {
        log.info(SERVICE_SERVICE + ": save()");
        Boolean serviceExist = serviceRepository.existsServiceByNom(serviceRequestDto.getNomService());
        if (serviceExist.equals(true))
            throw new ServiceExistException(String.format("service with nom %s already exists ", serviceRequestDto.getNomService()));
        return mapper.toDto(serviceRepository.save(mapper.toEntity(serviceRequestDto)));
    }

    @Override
    public List<ServiceResponseDto> findAll() {
        log.info(SERVICE_SERVICE + " : findAll()");
        return serviceRepository.findAll().stream().map(mapper::toDto).toList();
    }

    @Override
    public Optional<ServiceResponseDto> findById(Long id) throws ServiceNotFoundException {
        log.info(SERVICE_SERVICE + " with id {} : findById()",id);
        Service service = getServiceById(id);
        return Optional.of(mapper.toDto(service));
    }

    @Override
    public void update(ServiceRequestDto serviceRequestDto, Long id) throws ServiceNotFoundException {
        log.info(SERVICE_SERVICE + " with id {} : update()",id);
        Service service = getServiceById(id);
         mapper.updateToDto(serviceRequestDto,service);
         serviceRepository.save(service);
    }
    @Override
    public ServiceResponseDto updateOther(ServiceRequestDto serviceRequestDto, Long id) throws ServiceNotFoundException {
        log.info(SERVICE_SERVICE + " with id {} : updateOther()",id);
            getServiceById(id);
        Service service = mapper.toEntity(serviceRequestDto);
        service.setId(id);
        serviceRepository.saveAndFlush(service);
        return mapper.toDto(service);

    }

    @Override
    public void delete(Long id) throws ServiceNotFoundException {
        getServiceById(id);
        serviceRepository.deleteById(id);
    }

    private Service getServiceById(Long id) throws ServiceNotFoundException {
        log.info(SERVICE_SERVICE + " with id {} : getServiceById()",id);
        return serviceRepository.findById(id).orElseThrow(() -> new ServiceNotFoundException(String.format("service with id %s not found", id)));
    }

}
