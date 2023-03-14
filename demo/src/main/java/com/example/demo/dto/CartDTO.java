package com.example.demo.dto;

import com.example.demo.entity.Cart;
import com.example.demo.entity.Item;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class CartDTO {
    long cart_id;


    LocalDateTime cart_last_updated_on;


    List<ItemDTO> items= new ArrayList<>();
   public static CartDTO getDto(Cart cart){
       CartDTO cartDTO = new CartDTO();
       cartDTO.setCart_id(cart.getCartId());
       cartDTO.setCart_last_updated_on(cart.getCartLastUpdatedOn());
       cartDTO.setItems(cart.getItems().stream().map(i->ItemDTO.getDto(i)).collect(Collectors.toList()));
       return cartDTO;
   }
    public static Cart getEntity(CartDTO cartDTO){
        Cart cart = new Cart();
        cart.setCartId(cartDTO.getCart_id());
        cart.setCartLastUpdatedOn(cartDTO.getCart_last_updated_on());
        List<ItemDTO> items = cartDTO.getItems();
        List<Item> itemsEntity = items.stream().map(itemDTO -> ItemDTO.getEntity(itemDTO)).collect(Collectors.toList());
        cart.setItems(itemsEntity);
        return cart;
    }

}
