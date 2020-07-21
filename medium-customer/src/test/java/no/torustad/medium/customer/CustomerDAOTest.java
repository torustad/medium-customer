package no.torustad.medium.customer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import no.torustad.medium.customer.dao.CustomerDAO;
import no.torustad.medium.customer.dao.entity.CustomerEntity;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CustomerDAOTest {
    
    @Autowired
    private CustomerDAO customerDAO;

    @Test
        public void testSaveCustomer() {
            CustomerEntity testCustomer = 
                CustomerEntity.builder()
                .firstName("Ole olsen")
                .lastName("Hansen nilsen")
                .phoneNumber("313233")
                .email("a@b.c")
                .build();

            customerDAO.save(testCustomer);

            Optional<CustomerEntity> returnCustomer = customerDAO.findById(testCustomer.getCustomerId());

            assertTrue(returnCustomer.isPresent());
            assertEquals(returnCustomer, testCustomer);
        }
}