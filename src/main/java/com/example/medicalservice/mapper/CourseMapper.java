package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.ApproveRequest;
import com.example.medicalservice.domain.Course;
import com.example.medicalservice.domain.CourseStudent;
import com.example.medicalservice.domain.CourseTeacher;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author Li YaFeng
 * @date 2021/6/19 8:50
 */
@Mapper
public interface CourseMapper {
    int insertCourse(Course course);
    int updateCourse(Course course);
    Course findCourseById(Integer courseId);
    List<Course> findAllCourse();
    int deleteCourseById(Integer courseId);
    List<CourseTeacher> findCourseTeacherByCourseId(Integer courseId);
    int insertCourseTeacher(CourseTeacher courseTeacher);
    List<Course> findCourseByCourseName(String courseName);
    List<CourseTeacher> findCourseByTeacherId(Integer teacherId);
    CourseTeacher findCourseTeacherByTeacherIdAndCId(Integer teacherId,Integer courseId);
    int deleteCourseTeacher(CourseTeacher courseTeacher);
    int insertCourseStudent(CourseStudent courseStudent);
    CourseStudent findStudentBySIdCId(CourseStudent courseStudent);
    int deleteCourseStudent(CourseStudent courseStudent);
    List<CourseStudent> findCourseStudentByCId(Integer courseId);
    Integer updateCourseStudent(CourseStudent courseStudent);
    Integer insertApproveRequest(ApproveRequest approveRequest);
    ApproveRequest findRepeatAR(ApproveRequest approveRequest);
    List<ApproveRequest> findARByCourseId(Integer courseId);
    List<ApproveRequest> findARByStudentId(Integer studentId);
    Integer updateAR(ApproveRequest approveRequest);
    List<CourseStudent> findCourseByStudentId(Integer studentId);
    List<Course> findUnjoinCourseByStudentId(Integer studentId);
    List<CourseStudent> findInTeamStudentByCourseId(Integer courseId);
    List<CourseStudent> findNotTeamStudentByCId(Integer courseId);
    int upadateCourseTeam(Integer courseId,Integer teamId);
    int deleteOneStudentById(Integer courseId,Integer teamId,Integer studentId);
    List<CourseStudent> findTeamStudent(Integer courseId,Integer teamId);
    int updateOneStudentById(Integer courseId,Integer teamId,Integer studentId);
}
