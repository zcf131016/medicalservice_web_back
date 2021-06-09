package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
@ApiModel
public class CaseImage {
    private Integer id;
    private Integer caseId;
    private String imageUrl;
    private Date creatTime;

    @Override
    public String toString() {
        return "CaseImage{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", imageUrl='" + imageUrl + '\'' +
                ", creatTime=" + creatTime +
                '}';
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
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
