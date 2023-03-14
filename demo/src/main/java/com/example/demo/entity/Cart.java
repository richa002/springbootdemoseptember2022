package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString

public class Cart {
    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "cart_id", nullable = false)
    long cartId
            ;

    @Column(name = "cart_last_updated_on", nullable = false)
    LocalDateTime cartLastUpdatedOn;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    List<Item> items = new ArrayList<>();


}
