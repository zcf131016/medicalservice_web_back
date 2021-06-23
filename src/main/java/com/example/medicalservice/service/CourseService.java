package com.example.medicalservice.service;

import com.example.medicalservice.domain.ApproveRequest;
import com.example.medicalservice.domain.Course;
import com.example.medicalservice.domain.CourseStudent;
import com.example.medicalservice.domain.CourseTeacher;

import java.util.List;

/**
 * @author Li YaFeng
 * @date 2021/6/19 8:53
 */
public interface CourseService {
    List<Course> findAllCourse(Integer pageNum, Integer pageSize);//查找所有课程
    Course insertCourse(Course course);//新增课程
    Course updateCourse(Course course);//更新课程
    Integer deleteCourseById(Integer courseId);//根据课程id删除课程
    Course findCourseById(Integer courseId);//通过课程号查找课程
    Integer insertCourseTeacher(CourseTeacher courseTeacher);//添加课程老师
    List<Course> findCourseByCourseName(String courseName);//通过课程名称查找课程
    List<CourseTeacher> findCourseTeacherByCourseId(Integer courseId);//通过课程号查看本课程老师
    Integer deleteCourseTeacher(CourseTeacher courseTeacher);//删除课程老师
    List<CourseTeacher>findCourseByTeacherId(Integer teacherId);//通过教师号查看所教课程
    Integer insertCourseStudent(CourseStudent courseStudent);//添加课程学生
    Integer deleteCourseStudent(CourseStudent courseStudent);//删除课程学生
    List<CourseStudent> findCourseStudentByCId(Integer courseId);//根据课程id查找学生
    Integer updateCourseStudent(CourseStudent courseStudent);//对课程学生分组
    Integer insertApproveRequest(ApproveRequest approveRequest);//学生提交课程请求
    List<ApproveRequest> getRAByCourseId(Integer courseId);//通过课程号查找请求
    List<ApproveRequest> getRAByStudentId(Integer studentId);//通过学生号查找请求
    Integer updateAR(ApproveRequest approveRequest);//老师审批请求
    List<CourseStudent> findCourseByStudentId(Integer studentId);//通过学生号查找课程
    List<Course> findUnjoinCourseByStudentId(Integer studentId);//通过学生号查找其未加入课程
    List<CourseStudent> findNotTeamStudentByCId(Integer courseId);//查看班级未分组学生名单
}
