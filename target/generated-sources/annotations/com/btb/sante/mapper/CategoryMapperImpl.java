package com.btb.sante.mapper;

import com.btb.sante.dto.request.CategoryRequestDto;
import com.btb.sante.dto.response.CategoryResponseDto;
import com.btb.sante.entity.Category;
import com.btb.sante.entity.Service;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T12:56:34-0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category toEntity(CategoryRequestDto categoryRequestDto) {
        if ( categoryRequestDto == null ) {
            return null;
        }

        Category.CategoryBuilder category = Category.builder();

        category.service( categoryRequestDtoToService( categoryRequestDto ) );
        category.nom( categoryRequestDto.getNomCategory() );

        return category.build();
    }

    @Override
    public CategoryResponseDto toDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryResponseDto.CategoryResponseDtoBuilder categoryResponseDto = CategoryResponseDto.builder();

        categoryResponseDto.nomCategory( category.getNom() );
        categoryResponseDto.nomService( categoryServiceNom( category ) );
        categoryResponseDto.id( category.getId() );

        return categoryResponseDto.build();
    }

    @Override
    public void updateToDto(CategoryRequestDto categoryRequestDto, Category category) {
        if ( categoryRequestDto == null ) {
            return;
        }

        if ( category.getService() == null ) {
            category.setService( Service.builder().build() );
        }
        categoryRequestDtoToService1( categoryRequestDto, category.getService() );
        category.setNom( categoryRequestDto.getNomCategory() );
    }

    protected Service categoryRequestDtoToService(CategoryRequestDto categoryRequestDto) {
        if ( categoryRequestDto == null ) {
            return null;
        }

        Service.ServiceBuilder service = Service.builder();

        service.nom( categoryRequestDto.getNomService() );

        return service.build();
    }

    private String categoryServiceNom(Category category) {
        if ( category == null ) {
            return null;
        }
        Service service = category.getService();
        if ( service == null ) {
            return null;
        }
        String nom = service.getNom();
        if ( nom == null ) {
            return null;
        }
        return nom;
    }

    protected void categoryRequestDtoToService1(CategoryRequestDto categoryRequestDto, Service mappingTarget) {
        if ( categoryRequestDto == null ) {
            return;
        }

        mappingTarget.setNom( categoryRequestDto.getNomService() );
    }
}
