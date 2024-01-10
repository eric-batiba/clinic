package com.btb.sante.mapper;

import com.btb.sante.dto.request.TypeExamenRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.dto.response.TypeExamenResponseDto;
import com.btb.sante.entity.Category;
import com.btb.sante.entity.TypeExamen;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T12:56:33-0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class TypeExamMapperImpl implements TypeExamMapper {

    @Override
    public TypeExamen toEntity(TypeExamenRequestDto typeExamenRequestDto) {
        if ( typeExamenRequestDto == null ) {
            return null;
        }

        TypeExamen.TypeExamenBuilder typeExamen = TypeExamen.builder();

        typeExamen.category( typeExamenRequestDtoToCategory( typeExamenRequestDto ) );
        typeExamen.nom( typeExamenRequestDto.getNom() );

        return typeExamen.build();
    }

    @Override
    public TypeExamenResponseDto toDto(TypeExamen typeExamen) {
        if ( typeExamen == null ) {
            return null;
        }

        TypeExamenResponseDto.TypeExamenResponseDtoBuilder typeExamenResponseDto = TypeExamenResponseDto.builder();

        typeExamenResponseDto.categoryResponseDto( categoryToCategoryResponseDto( typeExamen.getCategory() ) );
        typeExamenResponseDto.id( typeExamen.getId() );
        typeExamenResponseDto.nom( typeExamen.getNom() );

        return typeExamenResponseDto.build();
    }

    @Override
    public void updateToDto(TypeExamenRequestDto typeExamenRequestDto, TypeExamen typeExamen) {
        if ( typeExamenRequestDto == null ) {
            return;
        }

        if ( typeExamen.getCategory() == null ) {
            typeExamen.setCategory( Category.builder().build() );
        }
        typeExamenRequestDtoToCategory1( typeExamenRequestDto, typeExamen.getCategory() );
        typeExamen.setNom( typeExamenRequestDto.getNom() );
    }

    protected Category typeExamenRequestDtoToCategory(TypeExamenRequestDto typeExamenRequestDto) {
        if ( typeExamenRequestDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.nom( typeExamenRequestDto.getNomCategory() );

        return category.build();
    }

    protected CategoryResponseDto categoryToCategoryResponseDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDto.CategoryResponseDtoBuilder categoryResponseDto = CategoryResponseDto.builder();

        categoryResponseDto.nomCategory( category.getNom() );
        categoryResponseDto.id( category.getId() );

        return categoryResponseDto.build();
    }

    protected void typeExamenRequestDtoToCategory1(TypeExamenRequestDto typeExamenRequestDto, Category mappingTarget) {
        if ( typeExamenRequestDto == null ) {
            return;
        }

        mappingTarget.setNom( typeExamenRequestDto.getNomCategory() );
    }
}
