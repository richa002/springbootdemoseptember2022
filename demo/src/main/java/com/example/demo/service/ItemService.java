package com.example.demo.service;


import com.example.demo.entity.Item;
import com.example.demo.entity.Orders;
import com.example.demo.repository.ItemRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Component
public class ItemService {
    @Autowired
    ItemRepository repository;


    public List<Item> getAll() {
        return repository.findAll();
    }

    @Transactional
    public Optional<Item> getById(long id) {
        Optional<Item> byId = repository.findById(id);
        if (byId.isPresent())
            System.out.println("Item fetched successfully : " + byId.get());
        else
            System.out.println("Item Not found");
        return byId;
    }



    public List<Item> findByItemPricePerUnitLessThan(double pricePerUnit){
        List<Item> items = repository.findByItemPricePerUnitLessThan(pricePerUnit);
        System.out.println("items fetched with price  per unit less than given price");
        items.forEach(System.out::println);
        return items;
    }



}
