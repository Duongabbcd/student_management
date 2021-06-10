package com.example.student_management.model;

import java.io.Serializable;

public class UserResponse implements Serializable {
    private long id ;
    private String username ;
    private String password ;
    private String fullName ;
    private String address ;
    private String phone ;
    private String email ;
    private String FacebookId ;

    public UserResponse() {
    }

    public UserResponse(long id, String username, String password, String fullName, String address, String phone, String email, String facebookId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        FacebookId = facebookId;
    }
    public UserResponse(long id) {
        this.id = id;

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacebookId() {
        return FacebookId;
    }

    public void setFacebookId(String facebookId) {
        FacebookId = facebookId;
    }
}
