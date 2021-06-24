package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Arrays;
import java.util.Date;
@ApiModel
public class CaseImage {
    private Integer id;
    private Integer caseId;
    private byte[] image;
    private Date creatTime;
    private String description;

    @Override
    public String toString() {
        return "CaseImage{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", image=" + Arrays.toString(image) +
                ", creatTime=" + creatTime +
                ", description='" + description + '\'' +
                '}';
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public byte[] getImage() {
        return image;
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
