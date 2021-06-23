package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;

@ApiModel
public class Course {
    private Integer id;
    private Integer courseId;
    private String courseName;
    private Integer teacherId;
    private String  courseDesc;
    private Integer courseState;
    private Date creatTime;
    private String teacherNumbers;
    private String creatTeacher;
    private List<Cases> cases;
    private List<CourseStudent> courseStudents;

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherId=" + teacherId +
                ", courseDesc='" + courseDesc + '\'' +
                ", courseState=" + courseState +
                ", creatTime=" + creatTime +
                ", teacherNumbers='" + teacherNumbers + '\'' +
                ", creatTeacher='" + creatTeacher + '\'' +
                ", cases=" + cases +
                ", courseStudents=" + courseStudents +
                '}';
    }

    public List<Cases> getCases() {
        return cases;
    }

    public void setCases(List<Cases> cases) {
        this.cases = cases;
    }

    public List<CourseStudent> getCourseStudents() {
        return courseStudents;
    }

    public void setCourseStudents(List<CourseStudent> courseStudents) {
        this.courseStudents = courseStudents;
    }

    public String getTeacherNumbers() {
        return teacherNumbers;
    }

    public void setTeacherNumbers(String teacherNumbers) {
        this.teacherNumbers = teacherNumbers;
    }

    public String getCreatTeacher() {
        return creatTeacher;
    }

    public void setCreatTeacher(String creatTeacher) {
        this.creatTeacher = creatTeacher;
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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getCourseDesc() {
        return courseDesc;
    }

    public void setCourseDesc(String courseDesc) {
        this.courseDesc = courseDesc;
    }

    public Integer getCourseState() {
        return courseState;
    }

    public void setCourseState(Integer courseState) {
        this.courseState = courseState;
    }
}
