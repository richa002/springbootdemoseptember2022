package com.example.demo.repository;

import com.example.demo.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository  extends JpaRepository<Address,Long> {
    List<Address> findByCountryAndCity(String country, String city);
    List<Address> findByCountryIgnoreCase(String country);
    List<Address> findByCityOrPincode(String city, String pincode);
    List<Address> findTop2ByCountry(String country);
    List<Address> findByCountryStartingWith(String substring);
    List<Address> findByCountryContaining(String substring);

}
