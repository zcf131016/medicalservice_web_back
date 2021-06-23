package com.example.medicalservice.domain;

import java.util.Date;

public class ApproveRequest {
    private Integer id;
    private Integer arId;
    private Integer courseId;
    private Integer isApproved;
    private Integer studentId;
    private String courseName;
    private String studentName;
    private Date createTime;

    @Override
    public String toString() {
        return "ApproveRequest{" +
                "id=" + id +
                ", arId=" + arId +
                ", courseId=" + courseId +
                ", isApproved=" + isApproved +
                ", studentId=" + studentId +
                ", courseName='" + courseName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", createTime=" + createTime +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArId() {
        return arId;
    }

    public void setArId(Integer arId) {
        this.arId = arId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
