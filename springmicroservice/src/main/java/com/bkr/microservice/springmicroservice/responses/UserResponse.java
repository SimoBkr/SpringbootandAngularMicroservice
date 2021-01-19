package com.bkr.microservice.springmicroservice.responses;

public class UserResponse {

    private String userId ;
    private String userName;
    private String lastName;
    private String email;
//
//    public UserResponse(String userId, String userName, String lastName, String email) {
//        this.userId = userId;
//        this.userName = userName;
//        this.lastName = lastName;
//        this.email = email;
//    }

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
