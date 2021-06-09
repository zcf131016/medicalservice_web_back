package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
@ApiModel
public class CaseFile {
    private Integer id;
    private Integer caseId;
    private String fileUrl;
    private Date creatTime;

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    @Override
    public String toString() {
        return "CaseFile{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", fileUrl='" + fileUrl + '\'' +
                ", creatTime=" + creatTime +
                '}';
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

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
