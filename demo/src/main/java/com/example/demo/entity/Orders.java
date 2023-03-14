package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
public class Orders {
    @Id
   long order_id;


    @ManyToOne
    @JoinColumn(name = "customer_id")
    Customer customer;

    @NotNull
    LocalDateTime order_creation__date;

    @OneToOne
    @JoinColumn(name = "cart_id")
    Cart cart;


}
