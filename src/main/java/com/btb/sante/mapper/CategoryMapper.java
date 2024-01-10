package com.btb.sante.mapper;

import com.btb.sante.dto.request.CategoryRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.entity.Category;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CategoryMapper {
    @Mapping(source = "nomCategory", target = "nom")
    @Mapping(source = "nomService", target = "service.nom")
    Category toEntity(CategoryRequestDto categoryRequestDto);

    @Mapping(source = "nom", target = "nomCategory")
    @Mapping(source = "service.nom", target = "nomService")
    CategoryResponseDto toDto(Category category);

    @Mapping(source = "nomCategory", target = "nom")
    @Mapping(source = "nomService", target = "service.nom")
    void updateToDto(CategoryRequestDto categoryRequestDto, @MappingTarget Category category);

}