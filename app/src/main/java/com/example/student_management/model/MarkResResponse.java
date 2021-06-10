package com.example.student_management.model;

import java.io.Serializable;

public class MarkResResponse implements Serializable {
    private Long id ;
    private double deligence ;
    private double midpoint1 ;
    private double midpoint2 ;
    private String status ;
    private String subjectName ;
    private String fullName ;
    private Long sub_id ;
    private Long user_id ;

    public MarkResResponse() {
    }

    public MarkResResponse(Long id, double deligence, double midpoint1, double midpoint2, String status, String subjectName, String fullName, Long sub_id, Long user_id) {
        this.id = id;
        this.deligence = deligence;
        this.midpoint1 = midpoint1;
        this.midpoint2 = midpoint2;
        this.status = status;
        this.subjectName = subjectName;
        this.fullName = fullName;
        this.sub_id = sub_id;
        this.user_id = user_id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDeligence() {
        return deligence;
    }

    public void setDeligence(double deligence) {
        this.deligence = deligence;
    }

    public double getMidpoint1() {
        return midpoint1;
    }

    public void setMidpoint1(double midpoint1) {
        this.midpoint1 = midpoint1;
    }

    public double getMidpoint2() {
        return midpoint2;
    }

    public void setMidpoint2(double midpoint2) {
        this.midpoint2 = midpoint2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Long getSub_id() {
        return sub_id;
    }

    public void setSub_id(Long sub_id) {
        this.sub_id = sub_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }
}
