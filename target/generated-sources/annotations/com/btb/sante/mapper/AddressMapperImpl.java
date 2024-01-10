package com.btb.sante.mapper;

import com.btb.sante.dto.request.AddressRequestDto;
import com.btb.sante.dto.response.AddressResponseDto;
import com.btb.sante.entity.Address;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-01-05T12:56:34-0800",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.7 (Eclipse Adoptium)"
)
@Component
public class AddressMapperImpl implements AddressMapper {

    @Override
    public Address toEntity(AddressRequestDto addressRequestDto) {
        if ( addressRequestDto == null ) {
            return null;
        }

        Address.AddressBuilder address = Address.builder();

        address.address1( addressRequestDto.getAddress1() );
        address.address2( addressRequestDto.getAddress2() );
        address.pays( addressRequestDto.getPays() );
        address.ville( addressRequestDto.getVille() );
        address.zipCode( addressRequestDto.getZipCode() );

        return address.build();
    }

    @Override
    public AddressResponseDto toDto(Address address) {
        if ( address == null ) {
            return null;
        }

        AddressResponseDto.AddressResponseDtoBuilder addressResponseDto = AddressResponseDto.builder();

        addressResponseDto.address1( address.getAddress1() );
        addressResponseDto.address2( address.getAddress2() );
        addressResponseDto.pays( address.getPays() );
        addressResponseDto.ville( address.getVille() );
        addressResponseDto.zipCode( address.getZipCode() );

        return addressResponseDto.build();
    }
}
