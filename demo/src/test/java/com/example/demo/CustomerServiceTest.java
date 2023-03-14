package com.example.demo;

import com.example.demo.entity.Address;
import com.example.demo.entity.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;



public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    // or we can also do

    @InjectMocks
    private CustomerService customerService;

    @BeforeEach
    public  void init() {
        //either use annotation @Mock or create mock yourself
       // CustomerRepository mockRepo = Mockito.mock(CustomerRepository.class);
        MockitoAnnotations.initMocks(this);
    }
//    @Test
//    public void invokeAOPService() {
//        Mockito.when(customerRepository.findAll()).thenReturn(new ArrayList<Customer>());
//        List<Customer> all = customerService.getAll();
//     Assertions.assertEquals(all,new ArrayList<Customer>());
//    }


    @Test
    public void hello(){
        customerService.hello();
    }
    @Test
    public void testGetById() {
        Customer c = new Customer();
        c.setAddress(new Address(2l,1,11,"city","state","country"));
        c.setCustomerId(2l);
        c.setFirstName("fisrtname");
        c.setLastName("last name");
        c.setPhone("phone");
        c.setEmail("email");
        c.setPassword("pass");
        Mockito.when(customerRepository.findById(2l)).thenReturn(Optional.of(c));
        Customer byId = customerService.getById(2l);

        Assertions.assertEquals(byId,c);
    }

}
