package com.example.demo.entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Address {
    @Id
    long address_id;
    int hous_no;
    int street;
    String city;
    String country;
    String pincode;

}
