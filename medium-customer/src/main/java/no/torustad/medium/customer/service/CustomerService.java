package no.torustad.medium.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import no.torustad.medium.customer.CustomerSystemException;
import no.torustad.medium.customer.dao.CustomerDAO;
import no.torustad.medium.customer.dao.entity.CustomerEntity;
import no.torustad.medium.customer.dao.mapper.CustomerEntityMapper;
import no.torustad.medium.customer.service.model.Customer;

@Service
@Slf4j
public class CustomerService {
    
    @Autowired
    CustomerDAO customerDAO;

    public Customer saveCustomer(Customer customer) {
        return persistCustomer(customer);   
    }

    private Customer persistCustomer(Customer customer) {
        try {
            CustomerEntity customerEntity = CustomerEntityMapper.INSTANCE.customerToCustomerEntity(customer);
            CustomerEntity storedEntity = customerDAO.save(customerEntity);
            Customer returnCustomer = CustomerEntityMapper.INSTANCE.customerEntityTCustomer(storedEntity);
            return returnCustomer;
        } catch (Exception e) {
            throw new CustomerSystemException("persistCustomer(): Unable to persist customer data: "+e.getMessage(), e);
        }
    }
}