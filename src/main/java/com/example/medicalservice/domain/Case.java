package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

@ApiModel
public class Case {
   private Integer id;
   private Integer caseId;
   private String caseName;
   private Integer courseId;
   private String courseName;
   private List<CaseImage> caseImages;
   private List<CaseFile> caseFiles;
   private Date creatTime;
    private String caseDesc;
    @Override
    public String toString() {
        return "Case{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", caseName='" + caseName + '\'' +
                ", courseId=" + courseId +
                ", caseImages=" + caseImages +
                ", caseFiles=" + caseFiles +
                ", creatTime=" + creatTime +
                ", courseName='" + courseName + '\'' +
                ", caseDesc='" + caseDesc + '\'' +
                '}';
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public List<CaseImage> getCaseImages() {
        return caseImages;
    }

    public void setCaseImages(List<CaseImage> caseImages) {
        this.caseImages = caseImages;
    }

    public List<CaseFile> getCaseFiles() {
        return caseFiles;
    }

    public void setCaseFiles(List<CaseFile> caseFiles) {
        this.caseFiles = caseFiles;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getCaseDesc() {
        return caseDesc;
    }

    public void setCaseDesc(String caseDesc) {
        this.caseDesc = caseDesc;
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

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }




}
