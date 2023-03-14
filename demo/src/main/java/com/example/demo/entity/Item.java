package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Item {
    @Id
   private long item_id;
    private String itemBarCode;
    private  String itemName;
    private double itemPricePerUnit;
    private int itemCount;

}
