package com.btb.sante.mapper;

import com.btb.sante.dto.request.PatientRequestDto;
import com.btb.sante.dto.response.PatientResponseDto;
import com.btb.sante.entity.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {AddressMapper.class,ExamenMapper.class}
        ,unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientMapper {
    @Mapping(source = "addressRequestDto",target = "address")
    @Mapping(source = "nomExam",target = "examen.nom")
    Patient toEntity(PatientRequestDto patientRequestDto);
    @Mapping(source = "address",target = "addressResponseDto")
    @Mapping(source = "examen",target = "examenResponseDto")
    PatientResponseDto toDto(Patient patient);
    @Mapping(source = "addressRequestDto",target = "address")
    @Mapping(source = "nomExam",target = "examen.nom")
    void update(PatientRequestDto patientRequestDto, @MappingTarget Patient patient);
}
