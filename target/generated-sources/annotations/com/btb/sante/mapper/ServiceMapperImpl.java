package com.btb.sante.mapper;

import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.entity.Service;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T13:04:31-0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class ServiceMapperImpl implements ServiceMapper {

    @Override
    public Service toEntity(ServiceRequestDto serviceRequestDto) {
        if ( serviceRequestDto == null ) {
            return null;
        }

        Service.ServiceBuilder service = Service.builder();

        service.nom( serviceRequestDto.getNomService() );

        return service.build();
    }

    @Override
    public ServiceResponseDto toDto(Service service) {
        if ( service == null ) {
            return null;
        }

        ServiceResponseDto.ServiceResponseDtoBuilder serviceResponseDto = ServiceResponseDto.builder();

        serviceResponseDto.id( service.getId() );

        return serviceResponseDto.build();
    }

    @Override
    public void updateToDto(ServiceRequestDto serviceRequestDto, Service service) {
        if ( serviceRequestDto == null ) {
            return;
        }

        service.setNom( serviceRequestDto.getNomService() );
    }
}
