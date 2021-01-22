package com.bkr.microservice.springmicroservice.responses;

import java.util.List;

public class UserResponse {

    private String userId ;
    private String userName;
    private String lastName;
    private String email;
    private List<AddressResponse> adresses;


    public List<AddressResponse> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AddressResponse> adresses) {
        this.adresses = adresses;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
