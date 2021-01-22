package com.bkr.microservice.springmicroservice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "users")
public class UserEntity implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false )
    private String userId;

    @Column(nullable = false )
    private String userName;

    @Column(nullable = false )
    private String lastName;

    @Column(nullable = false ,unique = true)
    private String email;

    @Column(nullable = false )
    private String encryptedpassword;

    @Column(nullable =true)
    private String emailVerificationToken;

    @Column(nullable = false )
    private Boolean emailVerificationStatus = false;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<AddresseEntity> adresses ;

    public List<AddresseEntity> getAdresses() {
        return adresses;
    }

    public void setAdresses(List<AddresseEntity> adresses) {
        this.adresses = adresses;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public String getEncryptedpassword() {
        return encryptedpassword;
    }

    public void setEncryptedpassword(String encryptedpassword) {
        this.encryptedpassword = encryptedpassword;
    }

    public String getEmailVerificationToken() {
        return emailVerificationToken;
    }

    public void setEmailVerificationToken(String emailVerificationToken) {
        this.emailVerificationToken = emailVerificationToken;
    }

    public Boolean getEmailVerificationStatus() {
        return emailVerificationStatus;
    }

    public void setEmailVerificationStatus(Boolean emailVerificationStatus) {
        this.emailVerificationStatus = emailVerificationStatus;
    }
}
