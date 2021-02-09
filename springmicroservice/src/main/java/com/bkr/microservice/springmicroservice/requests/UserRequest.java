package com.bkr.microservice.springmicroservice.requests;


import javax.validation.constraints.*;
import java.util.List;

public class UserRequest {

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 3,message = "Ce champ ne doit pas etre moins de 3 caractere")
    private String userName;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 3,message = "Ce champ ne doit pas etre moins de  caractere")
    private String lastName;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Email(message = "ce champ doit respectez le format email")
    private String email;

    @NotBlank(message = "Ce champ ne doit pas etre null")
    @Size(min = 8,message = "Ce champ ne doit pas etre moins de  8 caractere")
    @Size(max = 12,message = "Ce champ ne doit pas depsser 12 caracteres ")
    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$" , message = "ce mot de passe doit avoir des letters en Maj et Minsc et numero")
    private String password;

    private List<AddressRequest> addresses ;

    private ContactRequest contacts ;

    private Boolean admin;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<AddressRequest> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressRequest> addresses) {
        this.addresses = addresses;
    }

    public ContactRequest getContacts() {
        return contacts;
    }

    public void setContacts(ContactRequest contacts) {
        this.contacts = contacts;
    }
}
