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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
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
    PatientResponseDto patientResponseDtoUpdate;
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
        patientResponseDtoUpdate = PatientResponseDto.builder()
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
                .nom("Axel")
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
    void should_not_found_by_id() {
        Long id = 1L;
        //mock cal
        when(patientRepository.findById(id)).thenReturn(Optional.empty());
        //when, then
        assertThatThrownBy(()->patientService.findById(id))
                .isInstanceOf(PatientNoFoundException.class)
                .hasMessageContaining(String.format("le patient %s n'a pas ete trouvé", id));
    }
    @Test
    void should_find_By_Id() {
        Long id = 1L;
        //mock call
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(mapper.toDto(patient)).thenReturn(patientResponseDto);
        //WHEN
        Optional<PatientResponseDto> responseDto = patientService.findById(id);
        //THEN
        assertTrue(responseDto.isPresent());
        assertEquals(patient.getNom(),responseDto.get().getNom());
        //VERIFY
        verify(patientRepository,times(1)).findById(id);

    }
    @Test
    void should_find_All() {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        when(patientRepository.findAll()).thenReturn(patients);
        when(mapper.toDto(patient)).thenReturn(patientResponseDto);
        //WHEN
        List<PatientResponseDto> responseDto = patientService.findAll();
        //THEN
        assertEquals(patients.size(),responseDto.size());
        verify(patientRepository,times(1)).findAll();
        verify(mapper,times(1)).toDto(any());

    }


    @Test
    void should_update() throws ExamenNotFoundException {
        Long id = 1L;
        String nomExam = "palu";
        Patient patientSaved = patient;
        patientSaved.setId(1L);

        //mock call
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        when(examenRepository.findByNom(nomExam)).thenReturn(Optional.of(examen));
        when(mapper.toEntity(patientRequestDto)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patientSaved);
        when(mapper.toDto(patientSaved)).thenReturn(patientResponseDtoUpdate);
        //WHEN
        PatientResponseDto responseDto = patientService.update(patientRequestDto, id);
        //THEN
        assertNotEquals(patient.getNom(),responseDto.getNom());
        assertEquals(patientRequestDto.getNomExam(),responseDto.getExamenResponseDto().getNom());
        verify(patientRepository,times(1)).findById(id);
        verify(patientRepository,times(1)).save(any());
        verify(examenRepository,times(1)).findByNom(nomExam);
        verify(mapper,times(1)).toEntity(any());
        verify(mapper,times(1)).toDto(any());

    }

    @Test
    void should_delete_by_id() {
        Long id = 1L;
        when(patientRepository.findById(id)).thenReturn(Optional.of(patient));
        doNothing().when(patientRepository).deleteById(id);
        //WHEN
        patientService.delete(id);
        //VERIFY
        verify(patientRepository,times(1)).deleteById(id);

    }
}