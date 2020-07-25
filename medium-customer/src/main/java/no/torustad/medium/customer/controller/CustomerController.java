package no.torustad.medium.customer.controller;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import no.torustad.medium.customer.config.LoggingEnabled;
import no.torustad.medium.customer.controller.dto.CustomerDTO;
import no.torustad.medium.customer.controller.mapper.CustomerDTOMapper;
import no.torustad.medium.customer.service.CustomerService;
import no.torustad.medium.customer.service.model.Customer;

@Controller
@RequestMapping(value = "/customer")
@Slf4j
@LoggingEnabled
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CustomerDTO> saveCustomer(
        @RequestBody @Valid CustomerDTO customerDTO) throws URISyntaxException {
        
        Customer customer = CustomerDTOMapper.INSTANCE.customerDTOToCustomer(customerDTO);

        Customer savedCustomer = customerService.saveCustomer(customer);

        CustomerDTO savedCustomerDTO = CustomerDTOMapper.INSTANCE.customerToCustomerDTO(savedCustomer);

        return new ResponseEntity<CustomerDTO>(savedCustomerDTO, HttpStatus.CREATED);
    }

    @GetMapping(name = "/all", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CustomerDTO>> getAllCustomers() {

        //List<Customer> customerList = new ArrayList<>();

        //List<CustomerDTO> customerDTOList = CustomerDTOMapper.INSTANCE.customerToCustomerDTO(customerList);
     
        List<CustomerDTO> customerDTOList = new ArrayList<>();
        return new ResponseEntity<List<CustomerDTO>>(customerDTOList, HttpStatus.CREATED);
    }
}

