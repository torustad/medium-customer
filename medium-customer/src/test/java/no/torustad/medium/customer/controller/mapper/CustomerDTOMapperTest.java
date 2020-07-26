package no.torustad.medium.customer.controller.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerDTOMapperTest {
    @Test
    public void testNullCustomer() {
        Assertions.assertThat(CustomerDTOMapper.INSTANCE.customerToCustomerDTO(null)).isNull();
    }

    @Test
    public void testNullCustomerDTO() {
        Assertions.assertThat(CustomerDTOMapper.INSTANCE.customerDTOToCustomer(null)).isNull();
    }
    
}