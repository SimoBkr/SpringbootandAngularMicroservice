package com.bkr.microservice.springmicroservice.requests;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AddressRequest {

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 3,message = "Ce champ ne doit pas etre moins de 3 caractere")
    private String city ;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 3,message = "Ce champ ne doit pas etre moins de 3 caractere")
    private String country;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 5,message = "Ce champ ne doit pas etre moins de 3 caractere")
    private String street;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 4,message = "Ce champ ne doit pas etre moins de 3 caractere")
    private String postal ;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 3,message = "Ce champ ne doit pas etre moins de 3 caractere")
    private String type ;

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
