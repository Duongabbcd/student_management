package com.example.student_management.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class SubjectResponse implements Serializable {
    @SerializedName("id")
    private Long tvid;

    @SerializedName("subjectname")
    private String tvsubjectname;


    @SerializedName("quantity")
    private Long tvquantity;

    @SerializedName("semester")
    private long tvsemester;

    public SubjectResponse() {
    }

    public SubjectResponse(Long tvid, String tvsubjectname, Long tvquantity, long tvsemester) {
        this.tvid = tvid;
        this.tvsubjectname = tvsubjectname;
        this.tvquantity = tvquantity;
        this.tvsemester = tvsemester;
    }
    public SubjectResponse(Long tvid) {
        this.tvid = tvid;

    }

    public Long getTvid() {
        return tvid;
    }

    public void setTvid(Long tvid) {
        this.tvid = tvid;
    }

    public String getTvsubjectname() {
        return tvsubjectname;
    }

    public void setTvsubjectname(String tvsubjectname) {
        this.tvsubjectname = tvsubjectname;
    }

    public Long getTvquantity() {
        return tvquantity;
    }

    public void setTvquantity(Long tvquantity) {
        this.tvquantity = tvquantity;
    }

    public long getTvsemester() {
        return tvsemester;
    }

    public void setTvsemester(long tvsemester) {
        this.tvsemester = tvsemester;
    }
}