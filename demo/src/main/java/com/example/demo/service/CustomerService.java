package com.example.demo.service;


import com.example.demo.entity.Customer;
import com.example.demo.error.EntityNotFoundException;
import com.example.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class CustomerService {
    @Autowired
    CustomerRepository repository;

    @Transactional
    public void save(Customer customer) {
        repository.save(customer);
        System.out.println("Customer saved successfully");
    }

    public List<Customer> getAll() {
        return repository.findAll();
    }
    public void hello() {
        System.out.println("inside hello method");
    }

    public void methodThrowingException() {
        throw new RuntimeException("Error occurred");
    }


    public Customer getById(long id) {
        Optional<Customer> byId = repository.findById(id);
        if (byId.isPresent()) {
            System.out.println("Customer fetched successfully : " + byId.get());
            return  byId.get();
        }
        else {
            System.out.println("Not found");
            throw new EntityNotFoundException("Customer not found");
        }
    }


    public void deleteById(long id) {
       if( repository.getById(id)==null) throw new EntityNotFoundException("Customer not found");
      else {
           repository.deleteById(id);
           System.out.println("customer deleted successfully");
       }
    }

    public void findCustomerByFirstName(String name){
        List<Customer> list = repository.findByFirstName("richa");
        list.forEach(System.out::println);
    }


    public Customer updateProfile(long id, Customer customer) {


        Optional<Customer> byId = repository.findById(id);

        if(byId.isPresent()){
            Customer c = byId.get();
            c.setPassword(customer.getPassword());
            c.setUser_name(customer.getUser_name());
            c.setAddress(customer.getAddress());
            c.setEmail(customer.getEmail());
            c.setFirstName(customer.getFirstName());
            c.setLastName(customer.getLastName());
            c.setPhone(customer.getPhone());
           return repository.save(c);
        }
        else{
             throw new EntityNotFoundException("Customer not found");
        }
    }


}
