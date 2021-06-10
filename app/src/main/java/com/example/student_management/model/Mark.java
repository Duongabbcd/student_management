package com.example.student_management.model;

import java.io.Serializable;

public class Mark implements Serializable {
    private long mid ;
    private double deligence ;
    private double midpoint1;
    private double midpoint2 ;
    private String sta ;

  private SubjectResponse subjectResponse;
  private UserResponse userResponse;


    public Mark() {
    }

    public Mark(long mid, double deligence, double midpoint1, double midpoint2, String sta, SubjectResponse subjectResponse, UserResponse userResponse) {
        this.mid = mid;
        this.deligence = deligence;
        this.midpoint1 = midpoint1;
        this.midpoint2 = midpoint2;
        this.sta = sta;
        this.subjectResponse = subjectResponse;
        this.userResponse = userResponse;
    }

    public Mark(double deligence, double midpoint1, double midpoint2, String sta, SubjectResponse subjectResponse, UserResponse userResponse) {
        this.deligence = deligence;
        this.midpoint1 = midpoint1;
        this.midpoint2 = midpoint2;
        this.sta = sta;
        this.subjectResponse = subjectResponse;
        this.userResponse = userResponse;
    }

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
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

    public String getSta() {
        return sta;
    }

    public void setSta(String sta) {
        this.sta = sta;
    }

    public SubjectResponse getSubjectResponse() {
        return subjectResponse;
    }

    public void setSubjectResponse(SubjectResponse subjectResponse) {
        this.subjectResponse = subjectResponse;
    }

    public UserResponse getUserResponse() {
        return userResponse;
    }

    public void setUserResponse(UserResponse userResponse) {
        this.userResponse = userResponse;
    }
}
