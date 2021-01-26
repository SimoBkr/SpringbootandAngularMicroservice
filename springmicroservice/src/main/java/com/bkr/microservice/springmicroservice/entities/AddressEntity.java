package com.bkr.microservice.springmicroservice.entities;

import com.bkr.microservice.springmicroservice.shared.dto.UserDto;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "addresses")
public class AddressEntity implements Serializable {

    @Id @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String addressId;

    @Column(length = 20, nullable = false)
    private String city ;

    @Column(length = 20, nullable = false)
    private String country;

    @Column(length = 20, nullable = false)
    private String street;

    @Column(length = 20, nullable = false)
    private String postal ;

    @Column(length = 20, nullable = false)
    private String type ;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserEntity user;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getAdressId() {
        return addressId;
    }

    public void setAdressId(String addressId) {
        addressId = addressId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
