package com.example.student_management.model;

import java.io.Serializable;

public class SubjectResResponse implements Serializable {
    private String msg;

    public SubjectResResponse (){
    }

    public SubjectResResponse(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
