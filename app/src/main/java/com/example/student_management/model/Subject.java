package com.example.student_management.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Subject {

@SerializedName("id")
@Expose
private String id;

    public Subject(String id) {
        this.id = id;
    }

    public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

}