package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

@ApiModel
public class CourseStudent {
    private Integer id;
    private Integer courseId;
    private Integer studentId;
    private String courseName;
    private String studentName;
    private Integer teamId;
    private Date creatTime;
    private List<CourseStudent> courseStudents;
    private String creatTeacher;
    private Integer courseState;

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
                ", courseStudents=" + courseStudents +
                ", creatTeacher='" + creatTeacher + '\'' +
                ", courseState=" + courseState +
                '}';
    }

    public String getCreatTeacher() {
        return creatTeacher;
    }

    public void setCreatTeacher(String creatTeacher) {
        this.creatTeacher = creatTeacher;
    }

    public Integer getCourseState() {
        return courseState;
    }

    public void setCourseState(Integer courseState) {
        this.courseState = courseState;
    }

    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
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
