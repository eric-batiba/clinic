package com.btb.sante.mapper;

import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.entity.Examen;
import com.btb.sante.entity.Patient;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T12:56:34-0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class PatientMapperImpl implements PatientMapper {

    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private ExamenMapper examenMapper;

    @Override
    public Patient toEntity(PatientRequestDto patientRequestDto) {
        if ( patientRequestDto == null ) {
            return null;
        }

        Patient.PatientBuilder patient = Patient.builder();

        patient.examen( patientRequestDtoToExamen( patientRequestDto ) );
        patient.address( addressMapper.toEntity( patientRequestDto.getAddressRequestDto() ) );
        patient.nom( patientRequestDto.getNom() );
        patient.prenom( patientRequestDto.getPrenom() );
        patient.telephone( patientRequestDto.getTelephone() );
        patient.email( patientRequestDto.getEmail() );

        return patient.build();
    }

    @Override
    public PatientResponseDto toDto(Patient patient) {
        if ( patient == null ) {
            return null;
        }

        PatientResponseDto.PatientResponseDtoBuilder patientResponseDto = PatientResponseDto.builder();

        patientResponseDto.addressResponseDto( addressMapper.toDto( patient.getAddress() ) );
        patientResponseDto.examenResponseDto( examenMapper.toDto( patient.getExamen() ) );
        patientResponseDto.nom( patient.getNom() );
        patientResponseDto.prenom( patient.getPrenom() );
        patientResponseDto.telephone( patient.getTelephone() );
        patientResponseDto.email( patient.getEmail() );

        return patientResponseDto.build();
    }

    @Override
    public void update(PatientRequestDto patientRequestDto, Patient patient) {
        if ( patientRequestDto == null ) {
            return;
        }

        if ( patient.getExamen() == null ) {
            patient.setExamen( Examen.builder().build() );
        }
        patientRequestDtoToExamen1( patientRequestDto, patient.getExamen() );
        patient.setAddress( addressMapper.toEntity( patientRequestDto.getAddressRequestDto() ) );
        patient.setNom( patientRequestDto.getNom() );
        patient.setPrenom( patientRequestDto.getPrenom() );
        patient.setTelephone( patientRequestDto.getTelephone() );
        patient.setEmail( patientRequestDto.getEmail() );
    }

    protected Examen patientRequestDtoToExamen(PatientRequestDto patientRequestDto) {
        if ( patientRequestDto == null ) {
            return null;
        }

        Examen.ExamenBuilder examen = Examen.builder();

        examen.nom( patientRequestDto.getNomExam() );

        return examen.build();
    }

    protected void patientRequestDtoToExamen1(PatientRequestDto patientRequestDto, Examen mappingTarget) {
        if ( patientRequestDto == null ) {
            return;
        }

        mappingTarget.setNom( patientRequestDto.getNomExam() );
    }
}
