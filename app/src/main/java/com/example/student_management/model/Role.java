package com.example.student_management.model;

import java.io.Serializable;

public class Role implements Serializable {
    private Integer id;
    private UserRole name;

    public Role() {
    }

    public Role(UserRole name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserRole getName() {
        return name;
    }

    public void setName(UserRole name) {
        this.name = name;
    }
}
