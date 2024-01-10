package com.btb.sante.service.impl;

import com.btb.sante.dto.request.AddressRequestDto;
import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.AddressResponseDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.entity.*;
import com.btb.sante.exception.ExamenNotFoundException;
import com.btb.sante.exception.PatientNoFoundException;
import com.btb.sante.mapper.PatientMapper;
import com.btb.sante.repository.ConsultationRepository;
import com.btb.sante.repository.ExamenRepository;
import com.btb.sante.repository.NotificationRepository;
import com.btb.sante.repository.PatientRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PatientServiceTest {
    @InjectMocks
    private PatientService patientService;
    @Mock
    private PatientRepository patientRepository;
    @Mock
    private ExamenRepository examenRepository;
    @Mock
    private ConsultationRepository consultationRepository;
    @Mock
    private NotificationRepository notificationRepository;
    @Mock
    private PatientMapper mapper;
    private AutoCloseable autoCloseable;
    PatientRequestDto patientRequestDto;
    PatientResponseDto patientResponseDto;
    Patient patient;
    Examen examen;
    Consultation consultation;
    Notification notification;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);

         patientRequestDto = PatientRequestDto.builder().nom("biagul").nomExam("palu")
                .prenom("mp").email("biagul@gmail.com")
                .telephone(12345)
                 .nomExam("palu")
                .addressRequestDto(AddressRequestDto.builder()
                        .address1("8685 rue de la bonne entente")
                        .pays("canada")
                        .ville("Quebec city")
                        .zipCode("G2K 1C6")
                        .build())
                .build();
         patient = Patient.builder()
                 .nom("biagul")
                .prenom("mp")
                .email("biagul@gmail.com")
                .telephone(12345)
                .address(Address.builder()
                        .address1("8685 rue de la bonne entente")
                        .pays("canada")
                        .ville("Quebec city")
                        .zipCode("G2K 1C6")
                        .build())
                .build();

         examen = Examen.builder()
                .id(1L)
                .prix(new BigDecimal(15_000))
                .nom("palu")
                .pourcentage(5.0)
                .category(Category.builder().id(1L).nom("sante")
                        .service(Service.builder().id(1L).nom("labo").build())
                        .build())
                .build();

         patientResponseDto = PatientResponseDto.builder()
                .addressResponseDto(AddressResponseDto.builder()
                        .address1("8685 rue de la bonne entente")
                        .pays("canada")
                        .ville("Quebec city")
                        .zipCode("G2K 1C6")
                        .build())
                .examenResponseDto(ExamenResponseDto.builder()
                        .categoryResponseDto(CategoryResponseDto.builder()
                                .id(1L)
                                .nomCategory("labo").nomService("labo")
                                .build())
                        .prix(new BigDecimal(15_000))
                        .nom("palu")
                        .pourcentage(5.0)
                        .build())
                .nom("biagul")
                .prenom("mp")
                .email("biagul@gmail.com")
                .telephone(12345)
                .build();
         consultation = Consultation.builder()
                .id(ConsultationId.builder().examenId(1L).patientId(1L).build())
                .patient(patient)
                .examen(examen)
                .build();

         notification = Notification.builder()
                .consultation(consultation)
                .build();

    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void should_save_Patient() throws ExamenNotFoundException {
        String nom = "palu";
        Patient patientSaved = patient;
        patientSaved.setId(1L);
        //mock call
        when(examenRepository.findByNom(nom)).thenReturn(Optional.of(examen));
        when(mapper.toEntity(patientRequestDto)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patientSaved);
        when(consultationRepository.save(consultation)).thenReturn(any());
        when(notificationRepository.save(notification)).thenReturn(any());
        when(mapper.toDto(patientSaved)).thenReturn(patientResponseDto);
        //WHEN
        PatientResponseDto responseDto = patientService.savePatient(patientRequestDto);
        //THEN
        assertEquals(patientRequestDto.getNom(),responseDto.getNom());
        assertEquals(patientRequestDto.getNomExam(),responseDto.getExamenResponseDto().getNom());
        //VERIFY
        verify(examenRepository,times(1)).findByNom(nom);
        verify(patientRepository,times(1)).save(any());
        verify(consultationRepository,times(1)).save(any());
        verify(notificationRepository,times(1)).save(any());
        verify(mapper,times(1)).toEntity(any());
        verify(mapper,times(1)).toDto(any());
    }

    @Test
    void should_not_found_exam() {

        when(examenRepository.findByNom(patientRequestDto.getNomExam())).thenReturn(Optional.empty());
        assertThatThrownBy(()->patientService.savePatient(patientRequestDto))
                .isInstanceOf(ExamenNotFoundException.class)
                .hasMessageContaining(String.format("exam with %s not fond", patientRequestDto.getNomExam()));
    }
    @Test
    void findAll() {
    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}