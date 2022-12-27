package com.readingisgood.mapper;


import com.readingisgood.dto.AddressDTO;
import com.readingisgood.entity.Address;
import org.mapstruct.Mapper;

@Mapper(implementationName = "AddressMapperImpl", componentModel = "spring")
public interface IAddressMapper {
    Address toAddress(AddressDTO orderDTO);

    AddressDTO toAddressDTO(Address order);


}
