package com.bkr.microservice.springmicroservice.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy="user", fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private List<AddressEntity> addresses;

    @OneToOne(mappedBy = "user",cascade = CascadeType.ALL)
    private ContactEntity contacts;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "users" , cascade = CascadeType.ALL)
    private Set<GroupEntity> groups = new HashSet<>();

    @Column(nullable = false)
    private Boolean admin;

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
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

    public List<AddressEntity> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressEntity> addresses) {
        this.addresses = addresses;
    }

    public ContactEntity getContacts() {
        return contacts;
    }

    public void setContacts(ContactEntity contacts) {
        this.contacts = contacts;
    }
}
