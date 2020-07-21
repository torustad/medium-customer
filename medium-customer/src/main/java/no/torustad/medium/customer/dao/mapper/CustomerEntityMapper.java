package no.torustad.medium.customer.dao.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import no.torustad.medium.customer.dao.entity.CustomerEntity;
import no.torustad.medium.customer.service.model.Customer;

@Mapper
public interface CustomerEntityMapper {
    CustomerEntityMapper INSTANCE = 
        Mappers.getMapper(CustomerEntityMapper.class);

    CustomerEntity customerToCustomerEntity(Customer customer);
    Customer customerEntityTCustomer(CustomerEntity customerEntity);
}