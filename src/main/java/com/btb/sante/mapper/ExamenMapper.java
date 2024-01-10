package com.btb.sante.mapper;

import com.btb.sante.dto.request.ExamenRequestDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.entity.Examen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",uses = {CategoryMapper.class},unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ExamenMapper {
    @Mapping(source = "nomCategory",target = "category.nom")
    Examen toEntity(ExamenRequestDto examenRequestDto);
    @Mapping(source = "category",target = "categoryResponseDto")
    ExamenResponseDto toDto(Examen examen);
    @Mapping(source = "nomCategory",target = "category.nom")
    void update(ExamenRequestDto examenRequestDto, @MappingTarget Examen examen);
}
