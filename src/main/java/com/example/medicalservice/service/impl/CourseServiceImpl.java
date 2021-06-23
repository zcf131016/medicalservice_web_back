package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.*;
import com.example.medicalservice.mapper.CasesMapper;
import com.example.medicalservice.mapper.CourseMapper;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.service.CourseService;
import com.example.medicalservice.util.RandomUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Li YaFeng
 * @date 2021/6/19 8:53
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    UserMapper userMapper;
    @Autowired
    CasesMapper casesMapper;
    @Override
    public List<Course> findAllCourse(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Course> courses=courseMapper.findAllCourse();
        for (int i=0;i<courses.size();i++){
            List<CourseTeacher> courseTeachers=courseMapper.findCourseTeacherByCourseId(courses.get(i).getCourseId());
            String teacher="";
            for (int j=0;j<courseTeachers.size();j++){
                if (courseTeachers.get(j).getIsCreater()==1){
                    courses.get(i).setCreatTeacher(courseTeachers.get(j).getTeacherName());
                    System.out.println(courses.get(i).getCreatTeacher());
                    continue;
                }
                if (j<courseTeachers.size()-1){
                teacher+=courseTeachers.get(j).getTeacherName();
                teacher+="\n";
                }
                else
                    teacher+=courseTeachers.get(j).getTeacherName();
            }
            courses.get(i).setTeacherNumbers(teacher);
        }
        return courses;
    }

    @Override
    public Course insertCourse(Course course) {
        course.setCourseId(RandomUtil.getRandom(9));
        course.setCourseState(1);
        courseMapper.insertCourse(course);
        CourseTeacher courseTeacher=new CourseTeacher();
        courseTeacher.setCourseId(course.getCourseId());
        courseTeacher.setCourseName(course.getCourseName());
        courseTeacher.setIsCreater(1);
        courseTeacher.setTeacherName(userMapper.getUserByUserId(course.getTeacherId()).getUserName());//需调用用户mapper
        courseTeacher.setTeacherId(course.getTeacherId());
        courseMapper.insertCourseTeacher(courseTeacher);
        return course;
    }

    @Override
    public Course updateCourse(Course course) {
        courseMapper.updateCourse(course);
        return course;
    }

    @Override
    public Integer deleteCourseById(Integer courseId) {
        return courseMapper.deleteCourseById(courseId);
    }

    @Override
    public Course findCourseById(Integer courseId) {
        Course course=courseMapper.findCourseById(courseId);
        return course;
    }

    @Override
    public Integer insertCourseTeacher(CourseTeacher courseTeacher1) {
        List<CourseTeacher> courseTeachers=courseTeacher1.getCourseTeachers();
        if (courseMapper.findCourseTeacherByTeacherIdAndCId(courseTeacher1.getTeacherId(),courseTeacher1.getCourseId())==null||
                courseMapper.findCourseTeacherByTeacherIdAndCId(courseTeacher1.getTeacherId(),courseTeacher1.getCourseId()).getIsCreater()!=1){
            return 0;
        }
        for (int i=0;i<courseTeachers.size();i++){
            CourseTeacher courseTeacher=courseTeachers.get(i);
            courseTeacher.setIsCreater(0);
            courseTeacher.setTeacherName(userMapper.getUserByUserId(courseTeacher.getTeacherId()).getUserName());
            courseTeacher.setCourseId(courseTeacher1.getCourseId());
            courseTeacher.setCourseName(courseTeacher1.getCourseName());
            CourseTeacher courseTeacher2=courseMapper.findCourseTeacherByTeacherIdAndCId(courseTeacher.getTeacherId(),courseTeacher1.getCourseId());
            if (courseTeacher2==null)
               courseMapper.insertCourseTeacher(courseTeacher);
        }
        return 1;
    }

    @Override
    public List<Course> findCourseByCourseName(String courseName) {
        return courseMapper.findCourseByCourseName(courseName);
    }

    @Override
    public List<CourseTeacher> findCourseTeacherByCourseId(Integer courseId) {
        return courseMapper.findCourseTeacherByCourseId(courseId);
    }

    @Override
    public Integer deleteCourseTeacher(CourseTeacher courseTeacher) {
        return courseMapper.deleteCourseTeacher(courseTeacher);
    }

    @Override
    public List<CourseTeacher> findCourseByTeacherId(Integer teacherId) {
        return courseMapper.findCourseByTeacherId(teacherId);
    }

    @Override
    public Integer insertCourseStudent(CourseStudent courseStudent) {
        List<CourseStudent> courseStudents=courseStudent.getCourseStudents();
        for (int i=0;i<courseStudents.size();i++){
            CourseStudent one=courseStudents.get(i);
            one.setCourseId(courseStudent.getCourseId());
            one.setCourseName(courseStudent.getCourseName());
            one.setTeamId(-1);
            if(courseMapper.findStudentBySIdCId(one)==null)
              courseMapper.insertCourseStudent(one);
        }
        return 1;
    }

    @Override
    public Integer deleteCourseStudent(CourseStudent courseStudent) {
        return courseMapper.deleteCourseStudent(courseStudent);
    }

    @Override
    public List<CourseStudent> findCourseStudentByCId(Integer courseId) {
        return courseMapper.findCourseStudentByCId(courseId);
    }

    @Override
    public Integer updateCourseStudent(CourseStudent courseStudent) {
        List<CourseStudent> courseStudents=courseStudent.getCourseStudents();
        for (int i=0;i<courseStudents.size();i++){
            CourseStudent one=courseStudents.get(i);
            one.setTeamId(courseStudent.getTeamId());
            one.setCourseId(courseStudent.getCourseId());
            courseMapper.updateCourseStudent(one);
        }
        return 1;
    }

    @Override
    public Integer insertApproveRequest(ApproveRequest approveRequest) {

        if(courseMapper.findRepeatAR(approveRequest)!=null){
            return 2;
        }
        CourseStudent courseStudent=new CourseStudent();
        courseStudent.setCourseId(approveRequest.getCourseId());
        courseStudent.setStudentId(approveRequest.getStudentId());
        if (courseMapper.findStudentBySIdCId(courseStudent)!=null){
            return 0;
        }
        approveRequest.setArId(RandomUtil.getRandom(9));
        approveRequest.setIsApproved(1);
        courseMapper.insertApproveRequest(approveRequest);
        return 1;
    }

    @Override
    public List<ApproveRequest> getRAByCourseId(Integer courseId) {
        return courseMapper.findARByCourseId(courseId);
    }

    @Override
    public List<ApproveRequest> getRAByStudentId(Integer studentId) {
        return courseMapper.findARByStudentId(studentId);
    }

    @Override
    public Integer updateAR(ApproveRequest approveRequest) {
        if (approveRequest.getIsApproved()==2){
            CourseStudent courseStudent=new CourseStudent();
            courseStudent.setStudentId(approveRequest.getStudentId());
            courseStudent.setStudentName(approveRequest.getStudentName());
            courseStudent.setCourseId(approveRequest.getCourseId());
            courseStudent.setCourseName(approveRequest.getCourseName());
            courseStudent.setTeamId(-1);
            courseMapper.insertCourseStudent(courseStudent);
            courseMapper.updateAR(approveRequest);
        }else{
            courseMapper.updateAR(approveRequest);
        }
        return 1;
    }

    @Override
    public List<CourseStudent> findCourseByStudentId(Integer studentId) {
        return courseMapper.findCourseByStudentId(studentId);
    }

    @Override
    public List<Course> findUnjoinCourseByStudentId(Integer studentId) {
        List<Course> courses=courseMapper.findUnjoinCourseByStudentId(studentId);
        for (int i=0;i<courses.size();i++){
            Course course=courses.get(i);
            courses.get(i).setCreatTeacher(userMapper.getUserByUserId(course.getTeacherId()).getUserName());
        }
        return courses;
    }

    @Override
    public List<CourseStudent> findNotTeamStudentByCId(Integer courseId) {
        return courseMapper.findNotTeamStudentByCId(courseId);
    }

    @Override
    public List<CourseStudent> findInTeamStudentByCourseId(Integer courseId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<CourseStudent> courses=courseMapper.findInTeamStudentByCourseId(courseId);
        return courses;
    }

    @Override
    public Integer deleteTeamByCIdTId(Integer courseId, Integer teamId) {
        return courseMapper.upadateCourseTeam(courseId,teamId);
    }

    @Override
    public Integer deleteOneStudentById(Integer courseId, Integer teamId, Integer studentId) {
        return courseMapper.deleteOneStudentById(courseId,teamId,studentId);
    }

    @Override
    public List<CourseStudent> findTeamStudent(Integer courseId, Integer teamId) {
        return courseMapper.findTeamStudent(courseId,teamId);
    }

    @Override
    public Integer updateOneStudentById(Integer courseId, Integer teamId, Integer studentId) {
        return courseMapper.updateOneStudentById(courseId,teamId,studentId);
    }

}
