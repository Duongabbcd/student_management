package com.example.student_management.model;

import java.io.Serializable;

public class UserLoginResponse implements Serializable {
    private long id;
    private String username;
    private String fullName;
    private String address;
    private String phone;
    private String email;
    private String facebookId;
    private String[] roles;
    private String accessToken;
    private String tokenType;

    public UserLoginResponse() {
    }

    public UserLoginResponse(long id, String username, String fullName, String address, String phone, String email,
                             String facebookId, String[] roles, String accessToken, String tokenType) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.facebookId = facebookId;
        this.roles = roles;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
    }

    public UserLoginResponse(String username, String fullName, String address, String phone, String email,
                             String facebookId, String[] roles, String accessToken, String tokenType) {
        this.username = username;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.facebookId = facebookId;
        this.roles = roles;
        this.accessToken = accessToken;
        this.tokenType = tokenType;
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
        return facebookId;
    }

    public void setFacebookId(String facebookId) {
        this.facebookId = facebookId;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
