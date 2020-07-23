package no.torustad.medium.customer.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import no.torustad.medium.customer.controller.dto.CustomerDTO;
import no.torustad.medium.customer.dao.CustomerDAO;
import no.torustad.medium.customer.dao.entity.CustomerEntity;

// @ExtendWith(SpringExtension.class)
// @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// @AutoConfigureMockMvc
// @ActiveProfiles("test")
@SpringBootTest
public class CustomerControllerTest {
    
    @Autowired
    private CustomerDAO customerDAO;

    //@LocalServerPort
    int randomServerPort=10000;

    String customerPath = "/customer/";

    @BeforeEach
    public void setUp() {
        customerDAO.deleteAll();
    }

    @Test
    public void testAddCustomerSuccess() throws URISyntaxException {
        
        RestTemplate restTemplate = new RestTemplate();
        String baseURL = "http://localhost:" +randomServerPort+customerPath;
        URI uri = new URI(baseURL);    

        CustomerDTO customerDTO = CustomerDTO.builder()
                .firstName("Bård")
                .lastName("Tørustad")
                .email("baat@rcn.no")
                .phoneNumber("(612)602-4599")
                .build();

        org.springframework.http.HttpHeaders headers = new org.springframework.http.HttpHeaders(); // ????
        headers.set(org.springframework.http.HttpHeaders.CONTENT_TYPE, org.springframework.http.MediaType.APPLICATION_JSON_VALUE);

        HttpEntity<CustomerDTO> request = new HttpEntity<>(customerDTO, headers);

        System.out.println("****Calling...");
        ResponseEntity<CustomerDTO> result = restTemplate.postForEntity(uri, request, CustomerDTO.class);
        System.out.println("****Called...");
        // verify request success
        Assertions.assertEquals(201, result.getStatusCodeValue());
        Long newCustomerID = result.getBody().getCustomerId();
        Assertions.assertTrue(newCustomerID !=null && newCustomerID>0);
        //verify the state of the database
        Optional<CustomerEntity> storedEntityOptional = customerDAO.findById(newCustomerID);
        Assertions.assertTrue(storedEntityOptional.isPresent());
    }
}
