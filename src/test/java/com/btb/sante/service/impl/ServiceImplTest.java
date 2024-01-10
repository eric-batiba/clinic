package com.btb.sante.service.impl;

import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.entity.Service;
import com.btb.sante.exception.ServiceExistException;
import com.btb.sante.exception.ServiceNotFoundException;
import com.btb.sante.mapper.ServiceMapper;
import com.btb.sante.repository.ServiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

class ServiceImplTest {
    @InjectMocks
    private ServiceImpl serviceImpl;
    @Mock
    private ServiceRepository serviceRepository;
    @Mock
    private ServiceMapper serviceMapper;
    private AutoCloseable autoCloseable;
    @Captor
    private ArgumentCaptor<Service> captor;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
    }


    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void should_successful_saved_service() throws ServiceExistException {
        //GIVEN
        Service service = Service.builder().nom("myservice").build();
        ServiceRequestDto serviceRequestDto = ServiceRequestDto.builder()
                .nomService("myservice")
                .build();
        Service savedService = Service.builder().id(1L).nom("myservice").build();


        // mock the call
        when(serviceRepository.existsServiceByNom("myservice")).thenReturn(false);
        when(serviceMapper.toEntity(serviceRequestDto)).thenReturn(service);
        when(serviceRepository.save(service)).thenReturn(savedService);
        when(serviceMapper.toDto(savedService)).thenReturn(ServiceResponseDto.builder().id(1L).nomService("myservice").build());

        // WHEN
        ServiceResponseDto serviceResponseDto = serviceImpl.save(serviceRequestDto);

        // THEN
        assertEquals(serviceRequestDto.getNomService(), serviceResponseDto.getNomService());

        //VERIFY
        verify(serviceRepository, times(1)).existsServiceByNom("myservice");
        verify(serviceMapper, times(1)).toEntity(serviceRequestDto);
        verify(serviceRepository, timeout(1)).save(service);
        verify(serviceMapper, times(1)).toDto(savedService);

    }

    @Test
    void should_not_found_service() throws ServiceExistException {
        ServiceRequestDto serviceRequestDto = ServiceRequestDto.builder()
                .nomService("myservice")
                .build();

        when(serviceRepository.existsServiceByNom(anyString())).thenReturn(true);
        assertThatThrownBy(() -> serviceImpl.save(serviceRequestDto))
                .isInstanceOf(ServiceExistException.class)
                .hasMessageContaining("service with nom " + serviceRequestDto.getNomService() + " already exists ");
    }

    @Test
    void should_not_found_by_id() {
        Long id = 2L;
        when(serviceRepository.findById(id)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> serviceImpl.findById(id))
                .isInstanceOf(ServiceNotFoundException.class)
                .hasMessageContaining(String.format("service with id %s not found", id));
    }

    @Test
    void should_find_All_service() {
        ServiceResponseDto serviceResponseDto = ServiceResponseDto.builder().id(1L).nomService("myservice").build();
        List<Service> serviceList = List.of(Service.builder().id(1L).nom("myservice").build());

        //mock call mehod
        when(serviceRepository.findAll()).thenReturn(serviceList);
        when(serviceMapper.toDto(any(Service.class))).thenReturn(serviceResponseDto);

        //THEN
        List<ServiceResponseDto> serviceImplAll = serviceImpl.findAll();

        assertEquals(serviceList.size(), serviceImplAll.size());
        assertTrue(serviceImplAll.contains(serviceResponseDto));

        // VERIFY
        verify(serviceMapper, times(1)).toDto(any(Service.class));
        verify(serviceRepository, times(1)).findAll();
    }

    @Test
    void should_find_By_Id() throws ServiceNotFoundException {
        //GIVEN
        Long id = 1L;
        Service service = Service.builder().id(id).nom("myservice").build();

        //mock call
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));
        when(serviceMapper.toDto(any(Service.class))).thenReturn(ServiceResponseDto.builder().id(id).nomService("myservice").build());

        // WHEN
        Optional<ServiceResponseDto> serviceById = serviceImpl.findById(id);

        // THEN
        assertTrue(serviceById.isPresent());
        assertEquals(id, serviceById.get().getId());
        assertEquals("myservice", serviceById.get().getNomService());
        verify(serviceMapper, times(1)).toDto(any(Service.class));

    }

    @Test
    void should_updateOther() throws ServiceNotFoundException {
        //GIVEN
        Long id = 1L;
        Service service = Service.builder().id(id).nom("myservice").build();
        ServiceRequestDto serviceRequestDto = ServiceRequestDto.builder()
                .nomService("myservice2")
                .build();

        //mock call
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));
        when(serviceMapper.toEntity(serviceRequestDto)).thenReturn(service);
        when(serviceRepository.saveAndFlush(service)).thenReturn(service);
        when(serviceMapper.toDto(service)).thenReturn(ServiceResponseDto.builder().id(1L).nomService("myservice2").build());

        //WHEN
        ServiceResponseDto serviceResponseDto = serviceImpl.updateOther(serviceRequestDto, id);

        //THEN
        assertEquals(id, serviceResponseDto.getId());
        assertEquals(serviceResponseDto.getNomService(), serviceRequestDto.getNomService());

        //VERIFY
        verify(serviceMapper, times(1)).toDto(service);
        verify(serviceMapper, times(1)).toEntity(serviceRequestDto);
        verify(serviceRepository, times(1)).saveAndFlush(service);

    }

    @Test
    void update() throws ServiceNotFoundException {
        //GIVEN
        Long id = 1L;
        Service service = Service.builder().id(id).nom("myservice").build();
        ServiceRequestDto serviceRequestDto = ServiceRequestDto.builder()
                .nomService("myservice2")
                .build();
        //mock call method
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));
        doNothing().when(serviceMapper).updateToDto(serviceRequestDto, service);
        //WHEN
        serviceImpl.update(serviceRequestDto, id);
        //THEN
        then(serviceRepository).should().save(captor.capture());
        Service captorValue = captor.getValue();
        assertThat(captorValue).isEqualTo(service);

    }


    @Test
    void delete() throws ServiceNotFoundException {
        //GIVEN
        Long id = 1L;
        Service service = Service.builder().id(id).nom("myservice").build();
        when(serviceRepository.findById(id)).thenReturn(Optional.of(service));
        doNothing().when(serviceRepository).deleteById(id);
        // WHEN
        serviceImpl.delete(id);
        verify(serviceRepository).deleteById(id);

    }
}