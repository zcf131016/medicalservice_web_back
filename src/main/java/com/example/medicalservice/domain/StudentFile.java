package com.example.medicalservice.domain;

import java.util.Date;

/**
 * @author Lin YuHang
 * @date 2021/6/22 16:56
 */
public class StudentFile {
    private Integer id;
    private Integer caseId;
    private Integer studentId;
    private String filePath;
    private Date uploadTime;
    private String fileType;

    private String studentName;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getCaseName() {
        return caseName;
    }

    public void setCaseName(String caseName) {
        this.caseName = caseName;
    }

    private String caseName;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public String getFilePath() {
        return filePath;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public String getFileType() {
        return fileType;
    }
}
