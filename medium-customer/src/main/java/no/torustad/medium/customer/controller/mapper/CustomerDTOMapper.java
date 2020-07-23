package no.torustad.medium.customer.controller.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import no.torustad.medium.customer.controller.dto.CustomerDTO;
import no.torustad.medium.customer.service.model.Customer;

@Mapper
public interface CustomerDTOMapper {
    CustomerDTOMapper INSTANCE = Mappers.getMapper(CustomerDTOMapper.class);

    CustomerDTO customerToCustomerDTO(Customer customer);

    Customer customerDTOToCustomer(CustomerDTO customerDTO);

}