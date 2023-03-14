package com.example.demo.service;


import com.example.demo.dto.CartDTO;
import com.example.demo.dto.ItemDTO;
import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;
import com.example.demo.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CartService {
    @Autowired
    CartRepository customerRepository;
   @Transactional
    public CartDTO save(CartDTO cartDTO) {
        return CartDTO.getDto(customerRepository.save(CartDTO.getEntity(cartDTO)));
    }

    public List<CartDTO> getAll() {
        return customerRepository.findAll().stream().map(CartDTO::getDto).collect(Collectors.toList());
    }


        @Transactional
    public Optional<CartDTO> getById(long id) {
        Optional<Cart> byId = customerRepository.findById(id);

        List<Item> items1=null ;
        // if this line commented, item list will not be laoded due to lazy fetch, with this line, we will see 2 selelct queries in run, otherwise only one
        items1 = byId.get().getItems();

        Optional<CartDTO> cartDTO;

        // if items were loaded, set all proeprties with itemDTOlist  in DTO , OTHERWISE SET OTHER PROPERTIES ONLY
       if(items1!=null){
           cartDTO = byId.map(CartDTO::getDto);
       }
       else {
        cartDTO =  byId.map(cart -> {
               CartDTO dto = new CartDTO();
               dto.setCart_id(cart.getCartId());
               dto.setCart_last_updated_on(cart.getCartLastUpdatedOn());
               return dto;
           });
       }
        return cartDTO;
    }


    public void deleteById(long id) {
        customerRepository.deleteById(id);
        System.out.println("Cart deleted successfully");
    }


    public Optional<CartDTO> updateAddMoreItem(long id, List<ItemDTO> items) {
        List<Item> itemsEntity = items.stream().map(ItemDTO::getEntity).collect(Collectors.toList());
        Optional<Cart> cartById = customerRepository.findById(id);
        Optional<Cart> cart = cartById.map(c -> {
            c.getItems().addAll(itemsEntity);
            return customerRepository.save(c);
        });
        return cart.map(CartDTO::getDto);
    }


}
