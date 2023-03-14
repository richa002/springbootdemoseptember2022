package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item,Long> {

    List<Item> findByItemPricePerUnitLessThan(double price);
    List<Item> findByItemPricePerUnitLessThanEqual(double price);
    List<Item> findByItemPricePerUnitBetween(double price1, double price2);

}
