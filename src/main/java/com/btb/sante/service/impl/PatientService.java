package com.btb.sante.service.impl;

import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.entity.*;
import com.btb.sante.exception.ExamenNotFoundException;
import com.btb.sante.exception.PatientNoFoundException;
import com.btb.sante.mapper.PatientMapper;
import com.btb.sante.repository.ConsultationRepository;
import com.btb.sante.repository.ExamenRepository;
import com.btb.sante.repository.NotificationRepository;
import com.btb.sante.repository.PatientRepository;
import com.btb.sante.service.Ipatient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
@Slf4j
public class PatientService implements Ipatient {
    public static final String PATIENT_SERVICE = "PatientService";
    private final PatientRepository patientRepository;
    private final ExamenRepository examenRepository;
    private final ConsultationRepository consultationRepository;
    private final NotificationRepository notificationRepository;
    private final PatientMapper mapper;
    @Override
    public PatientResponseDto savePatient(PatientRequestDto patientRequestDto) throws ExamenNotFoundException {
        log.info(PATIENT_SERVICE + " :: savePatient()");
        Examen examen = getExamenByNom(patientRequestDto);
        Patient patient = mapper.toEntity(patientRequestDto);
        patient.setExamen(examen);

        Consultation consultation = Consultation.builder()
                .id(ConsultationId.builder().examenId(examen.getId()).patientId(patient.getId()).build())
                .examen(examen)
                .patient(patient)
                .build();

        Patient savedPatient = patientRepository.save(patient);
        consultationRepository.save(consultation);
        notificationRepository.save(Notification.builder().consultation(consultation).build());
        return mapper.toDto(savedPatient);
    }

    @Override
    public List<PatientResponseDto> findAll() {
        log.info(PATIENT_SERVICE + " :: findAll()");
        return patientRepository.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public Optional<PatientResponseDto> findById(Long id) {
        log.info(PATIENT_SERVICE + " with id{} :: findById()",id);
        return Optional.of(mapper.toDto(getPatient(id)));
    }

    @Override
    public PatientResponseDto update(PatientRequestDto patientRequestDto, Long id) throws ExamenNotFoundException {
        log.info(PATIENT_SERVICE + " with id {} :: update()",id);
        getPatient(id);
        Examen examen = getExamenByNom(patientRequestDto);
        Patient patient = mapper.toEntity(patientRequestDto);
        patient.setId(id);
        patient.setExamen(examen);
        return mapper.toDto(patientRepository.save(patient));
    }

    @Override
    public void delete(Long id) {
        log.info(PATIENT_SERVICE + " with id {} :: delete()",id);
        getPatient(id);
        patientRepository.deleteById(id);
    }

    private Patient getPatient(Long id) {
        return patientRepository.findById(id).orElseThrow(() -> new PatientNoFoundException(String.format("le patient %s n'a pas ete trouvé", id)));
    }
    private Examen getExamenByNom(PatientRequestDto patientRequestDto) throws ExamenNotFoundException {
        return examenRepository.findByNom(patientRequestDto.getNomExam()).orElseThrow(() -> new ExamenNotFoundException(String.format("exam with %s not fond", patientRequestDto.getNomExam())));
    }
}
