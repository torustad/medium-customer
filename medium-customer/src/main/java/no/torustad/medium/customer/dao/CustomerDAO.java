package no.torustad.medium.customer.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import no.torustad.medium.customer.dao.entity.CustomerEntity;

@Repository
public interface CustomerDAO extends CrudRepository<CustomerEntity, Long> {    
}