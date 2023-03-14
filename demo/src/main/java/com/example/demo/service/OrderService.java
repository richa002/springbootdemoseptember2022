package com.example.demo.service;


import com.example.demo.entity.Customer;
import com.example.demo.entity.Orders;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class OrderService {
    @Autowired
    OrderRepository repository;

    @Transactional
    public void save(Orders order) {
        repository.save(order);
        System.out.println("Order saved successfully");
    }

    public List<Orders> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Orders> getById(long id) {
        Optional<Orders> byId = repository.findById(id);
        if (byId.isPresent())
            System.out.println("Order fetched successfully : " + byId.get());
        else
            System.out.println("Order Not found");
        return byId;
    }


    public void deleteById(long id) {
        repository.deleteById(id);
        System.out.println("Order deleted successfully");
    }

    public List<Orders> getOrdersOfCustomer(long customerId){
        List<Orders> orders = repository.findByCustomer_CustomerId(customerId);
        System.out.println("Orders fetched for a customer");
        orders.forEach(System.out::println);
        return orders;
    }



}
