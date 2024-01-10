package com.btb.sante.mapper;

import com.btb.sante.dto.request.TypeExamenRequestDto;
import com.btb.sante.dto.response.TypeExamenResponseDto;
import com.btb.sante.entity.TypeExamen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TypeExamMapper {
    @Mapping(source = "nomCategory", target = "category.nom")
    TypeExamen toEntity(TypeExamenRequestDto typeExamenRequestDto);

    @Mapping(source = "category.nom", target = "categoryResponseDto.nomCategory")
    TypeExamenResponseDto toDto(TypeExamen typeExamen);

    @Mapping(source = "nomCategory", target = "category.nom")
    void updateToDto(TypeExamenRequestDto typeExamenRequestDto, @MappingTarget TypeExamen typeExamen);

}