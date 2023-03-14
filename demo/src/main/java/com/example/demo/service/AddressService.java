package com.example.demo.service;


import com.example.demo.entity.Address;
import com.example.demo.entity.Item;
import com.example.demo.repository.AddressRepository;
import com.example.demo.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class AddressService {
    @Autowired
    AddressRepository repository;


    public List<Address> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Address> getById(long id) {
        Optional<Address> byId = repository.findById(id);
        if (byId.isPresent())
            System.out.println("Address fetched successfully : " + byId.get());
        else
            System.out.println("Address Not found");
        return byId;
    }



    public List<Address> findByCountryStartingWith(String countryPrefix){
        List<Address> list = repository.findByCountryStartingWith(countryPrefix);
        System.out.println("Addresses fetched with country name begin with  given prefix");
        list.forEach(System.out::println);
        return list;
    }



}
