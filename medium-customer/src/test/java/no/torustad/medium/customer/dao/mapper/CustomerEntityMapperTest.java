package no.torustad.medium.customer.dao.mapper;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class CustomerEntityMapperTest {
    @Test
    public void testNullCustomer() {
        Assertions.assertThat(CustomerEntityMapper.INSTANCE.customerToCustomerEntity(null)).isNull();
    }

    @Test
    public void testNullCustomerDTO() {
        Assertions.assertThat(CustomerEntityMapper.INSTANCE.customerEntityTCustomer(null)).isNull();
    }
}