package no.torustad.medium.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import no.torustad.medium.customer.controller.CustomerController;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private CustomerController customerController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(customerController).isNotNull();
	}

}
