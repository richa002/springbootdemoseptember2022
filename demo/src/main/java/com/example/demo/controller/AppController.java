package com.example.demo.controller;

import com.example.demo.entity.Customer;
import com.example.demo.error.EntityNotFoundException;
import com.example.demo.error.ErrorMessage;
import com.example.demo.service.CustomerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityExistsException;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/customers/")
public class AppController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/hello")
    public String greet() {
        return " hello to the app";
    }

    @GetMapping("/hello/{id}/{subId}") // /hello/2/201?name=richa&age=2
    public String test(@PathVariable("id") Long id,
                       @PathVariable("subId") Long subId,
                       @RequestParam("name") String name) {
        return " hello " + name + " ," + id + " ," + subId + " ";
    }

    @ApiOperation("Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found the customer"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // for both path param and query param @ApiParam is used for swagger documentation
    public Customer fetchCustomer(@ApiParam(name = "id", value = "Id of customer", required = true, example = "1")
                                  @PathVariable("id") Long custId) {

        return customerService.getById(custId);
    }

    @ApiOperation("Get a customer by its id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Found the customer"),
            @ApiResponse(code = 400, message = "Invalid id supplied"),
    })
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    // for both path param and query param @ApiParam is used for swagger documentation
    public Customer fetchCustomer2(@ApiParam(name = "id", value = "Id of customer", required = true, example = "1")
                                  @PathVariable("id") Long custId) {

        return customerService.getById(custId);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Customer> fetchAllCustomers() {

        return customerService.getAll();
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Object> createCustomer(@RequestBody Customer customer) {
        if (customer == null || customer.getCustomerId() <= 0) {
            return ResponseEntity.badRequest().body(new ErrorMessage("Invalid Rewuest", 10002));
        }
        customerService.save(customer);
        return ResponseEntity.ok("SAVED SUCCESSFULLY");

    }

    @PutMapping("book/{id}")// ---> book/2
    public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long custId, @RequestBody Customer customer) {
       return ResponseEntity.ok(customerService.updateProfile(custId, customer));
    }

    @PutMapping("book/xyz/{id}")// ---> book/2
    public ResponseEntity<Customer> updateCustomer2(@PathVariable("id") Long custId, @RequestBody Customer customer) {
        return ResponseEntity.ok(customerService.updateProfile(custId, customer));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable("id") Long custId) {
        customerService.deleteById(custId);
        return ResponseEntity.ok("Customer deleted successfully");
    }
}
