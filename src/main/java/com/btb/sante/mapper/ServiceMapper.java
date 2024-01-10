package com.btb.sante.mapper;

import com.btb.sante.dto.request.ServiceRequestDto;
import com.btb.sante.dto.response.ServiceResponseDto;
import com.btb.sante.entity.Service;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ServiceMapper {
    @Mapping(source = "nomService", target = "nom")
    Service toEntity(ServiceRequestDto serviceRequestDto);

    @InheritInverseConfiguration(name = "toEntity")
    ServiceResponseDto toDto(Service service);

    @Mapping(source = "nomService", target = "nom")
   // @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateToDto(ServiceRequestDto serviceRequestDto, @MappingTarget Service service);
}
