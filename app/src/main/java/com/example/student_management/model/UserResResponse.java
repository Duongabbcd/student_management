package com.example.student_management.model;

import java.io.Serializable;

public class UserResResponse implements Serializable {
    private String message ;

    public UserResResponse() {
    }

    public UserResResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
