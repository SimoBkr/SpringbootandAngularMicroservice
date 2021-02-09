package com.bkr.microservice.springmicroservice.responses;

import java.util.List;

public class UserResponse {

    private String userId ;
    private String userName;
    private String lastName;
    private String email;
    private List<AddressResponse> addresses;
    private ContactResponse contacts ;
    private Boolean admin;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public List<AddressResponse> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressResponse> addresses) {
        this.addresses = addresses;
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

    public ContactResponse getContacts() {
        return contacts;
    }

    public void setContacts(ContactResponse contacts) {
        this.contacts = contacts;
    }
}
