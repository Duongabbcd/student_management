package com.example.student_management.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MarkResponse {
    @SerializedName("id")
    @Expose
    private String id;

@SerializedName("deligence")
@Expose
private String deligence;
@SerializedName("midpoint1")
@Expose
private String midpoint1;
@SerializedName("midpoint2")
@Expose
private String midpoint2;
@SerializedName("status")
@Expose
private String status;
@SerializedName("subject")
@Expose
private Subject subject;
@SerializedName("user")
@Expose
private User user;

    public MarkResponse(String deligence, String midpoint1, String midpoint2, String status, Subject subject, User user) {
        this.deligence = deligence;
        this.midpoint1 = midpoint1;
        this.midpoint2 = midpoint2;
        this.status = status;
        this.subject = subject;
        this.user = user;
    }


    public MarkResponse(String id, String deligence, String midpoint1, String midpoint2, String status, Subject subject, User user) {
        this.id = id;
        this.deligence = deligence;
        this.midpoint1 = midpoint1;
        this.midpoint2 = midpoint2;
        this.status = status;
        this.subject = subject;
        this.user = user;
    }

    public String getDeligence() {
return deligence;
}

public void setDeligence(String deligence) {
this.deligence = deligence;
}

public String getMidpoint1() {
return midpoint1;
}

public void setMidpoint1(String midpoint1) {
this.midpoint1 = midpoint1;
}

public String getMidpoint2() {
return midpoint2;
}

public void setMidpoint2(String midpoint2) {
this.midpoint2 = midpoint2;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public Subject getSubject() {
return subject;
}

public void setSubject(Subject subject) {
this.subject = subject;
}

public User getUser() {
return user;
}

public void setUser(User user) {
this.user = user;
}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}