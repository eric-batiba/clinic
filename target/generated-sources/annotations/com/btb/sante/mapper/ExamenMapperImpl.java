package com.btb.sante.mapper;

import com.btb.sante.dto.request.ExamenRequestDto;
import com.btb.sante.dto.response.ExamenResponseDto;
import com.btb.sante.entity.Category;
import com.btb.sante.entity.Examen;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T12:56:34-0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ExamenMapperImpl implements ExamenMapper {

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Examen toEntity(ExamenRequestDto examenRequestDto) {
        if ( examenRequestDto == null ) {
            return null;
        }

        Examen.ExamenBuilder examen = Examen.builder();

        examen.category( examenRequestDtoToCategory( examenRequestDto ) );
        examen.id( examenRequestDto.getId() );
        examen.nom( examenRequestDto.getNom() );
        examen.prix( examenRequestDto.getPrix() );
        examen.pourcentage( examenRequestDto.getPourcentage() );

        return examen.build();
    }

    @Override
    public ExamenResponseDto toDto(Examen examen) {
        if ( examen == null ) {
            return null;
        }

        ExamenResponseDto.ExamenResponseDtoBuilder examenResponseDto = ExamenResponseDto.builder();

        examenResponseDto.categoryResponseDto( categoryMapper.toDto( examen.getCategory() ) );
        examenResponseDto.id( examen.getId() );
        examenResponseDto.nom( examen.getNom() );
        examenResponseDto.prix( examen.getPrix() );
        examenResponseDto.pourcentage( examen.getPourcentage() );

        return examenResponseDto.build();
    }

    @Override
    public void update(ExamenRequestDto examenRequestDto, Examen examen) {
        if ( examenRequestDto == null ) {
            return;
        }

        if ( examen.getCategory() == null ) {
            examen.setCategory( Category.builder().build() );
        }
        examenRequestDtoToCategory1( examenRequestDto, examen.getCategory() );
        examen.setId( examenRequestDto.getId() );
        examen.setNom( examenRequestDto.getNom() );
        examen.setPrix( examenRequestDto.getPrix() );
        examen.setPourcentage( examenRequestDto.getPourcentage() );
    }

    protected Category examenRequestDtoToCategory(ExamenRequestDto examenRequestDto) {
        if ( examenRequestDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.nom( examenRequestDto.getNomCategory() );

        return category.build();
    }

    protected void examenRequestDtoToCategory1(ExamenRequestDto examenRequestDto, Category mappingTarget) {
        if ( examenRequestDto == null ) {
            return;
        }

        mappingTarget.setNom( examenRequestDto.getNomCategory() );
    }
}
