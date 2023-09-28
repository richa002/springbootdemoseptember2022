package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class Customer {
    @Id
    @ApiModelProperty(notes = "Customer Id", example = "1321", required = true)
    long customerId;
    @ApiModelProperty(notes = "User Id of customer", example = "abc00342", required = true)
    String user_name;
    @ApiModelProperty(notes = "First name of customer", example = "James", required = true)
    String firstName;
    @ApiModelProperty(notes = "Last name of customer", example = "Smith", required = false)
    String lastName;
    @ApiModelProperty(notes = "Email of customer", example = "Smit@gmail.comh", required = false)
    String email;
    @ApiModelProperty(notes = "Phone number of customer", example = "3176781638", required = false)
    String phone;
    @ApiModelProperty(notes = "Password of customer", example = "Smith", required = false)
    String password;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")

    @ApiModelProperty(notes = "Address of customer", required = false)
    Address address;
}
