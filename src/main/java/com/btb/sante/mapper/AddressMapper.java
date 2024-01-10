package com.btb.sante.mapper;

import com.btb.sante.dto.request.AddressRequestDto;
import com.btb.sante.dto.response.AddressResponseDto;
import com.btb.sante.entity.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {
    Address toEntity(AddressRequestDto addressRequestDto);
    AddressResponseDto toDto(Address address);
}
