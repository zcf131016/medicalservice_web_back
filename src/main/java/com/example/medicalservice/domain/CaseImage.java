package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Arrays;
import java.util.Date;
@ApiModel
public class CaseImage {
    private Integer id;
    private Integer caseId;
    private Object image;
    private Date creatTime;

    @Override
    public String toString() {
        return "CaseImage{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", image=" + image +
                ", creatTime=" + creatTime +
                '}';
    }

    public Object getImage() {
        return image;
    }

    public void setImage(Object image) {
        this.image = image;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }
}
