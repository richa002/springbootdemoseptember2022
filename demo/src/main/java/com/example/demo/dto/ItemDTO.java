package com.example.demo.dto;

import com.example.demo.entity.Item;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ItemDTO {
    private long item_id;
    private String itemBarCode;
    private  String item_name;
    private double item_price_per_unit;
    private int item_count;

    public static ItemDTO getDto(Item item){
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setItem_id(item.getItem_id());
        itemDTO.setItem_count(item.getItemCount());
        itemDTO.setItemBarCode(item.getItemBarCode());
        itemDTO.setItem_name(item.getItemName());
        itemDTO.setItem_price_per_unit(item.getItemPricePerUnit());
        return itemDTO;
    }
    public static Item getEntity(ItemDTO dto){
        Item item = new Item();
        item.setItem_id(dto.getItem_id());
        item.setItemCount(dto.getItem_count());
        item.setItemBarCode(dto.getItemBarCode());
        item.setItemName(dto.getItem_name());
        item.setItemPricePerUnit(dto.getItem_price_per_unit());
        return item;
    }}
