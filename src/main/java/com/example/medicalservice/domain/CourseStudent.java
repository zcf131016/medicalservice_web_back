package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
@ApiModel
public class CourseStudent {
    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private String courseName;
    private String studentName;
    private Integer teamId;
    private Date creatTime;

    @Override
    public String toString() {
        return "CourseStudent{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", studentId=" + studentId +
                ", courseName='" + courseName + '\'' +
                ", studentName='" + studentName + '\'' +
                ", teamId=" + teamId +
                ", creatTime=" + creatTime +
                '}';
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public Integer getTeamId() {
        return teamId;
    }

    public void setTeamId(Integer teamId) {
        this.teamId = teamId;
    }
}
