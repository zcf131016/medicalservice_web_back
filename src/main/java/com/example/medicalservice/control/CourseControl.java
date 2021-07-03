package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.domain.*;
import com.example.medicalservice.service.CourseService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Api(tags = "课程管理")
@RestController
@RequestMapping("/course")
public class CourseControl {
    @Autowired
    CourseService courseService;

    @ApiOperation(value = "查找所有课程")
    @ResponseBody
    @GetMapping("/findAllCourse/{pageNum}/{pageSize}")
    public Result getAllCourse(@PathVariable Integer pageNum,@PathVariable Integer pageSize) {
        JSONObject json = new JSONObject();
         PageInfo pageInfo =courseService.findAllCourse(pageNum,pageSize);
        json.put("pageInfo", pageInfo);
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("分页查找课程成功");
    }
    @ApiOperation(value = "通过课程id查找课程" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId",dataType = "INTEGER",value="课程id"),
    })
    @ResponseBody
    @GetMapping("/findCourseByCourseId/{courseId}")
    public Result getCourseByCourseId(@PathVariable Integer courseId) {
        Course courses=courseService.findCourseById(courseId);
        return Result.success().setData(courses).setCode(ResultCodeEnum.OK.getCode()).setMsg("根据课程Id查找课程成功");
    }
    @ApiOperation(value = "通过课程名称查找课程" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseName", value="课程名称"),
    })
    @ResponseBody
    @GetMapping("/findCourseByCourseName/{courseName}")
    public Result getCourseByCourseName(@PathVariable String courseName) {
        List<Course> courses=courseService.findCourseByCourseName(courseName);
        int Count = courses.size();
        if (Count==0){
            return Result.failure(ResultCodeEnum.CREATED).setMsg("此课程为空请输入正确的课程名称");
        }
        return Result.success().setData(courses).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("根据课程名查找课程成功");
    }
    @ApiOperation(value = "新增课程")
    @ResponseBody
    @PostMapping("/insertCourse")
    public Result insertCourse(@RequestBody Course course) {
        return Result.success().setData(courseService.insertCourse(course)).setCode(ResultCodeEnum.OK.getCode()).setMsg("添加课程成功");
    }
    @ApiOperation(value = "修改课程")
    @ResponseBody
    @PostMapping("/updateCourse")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
    })
    public Result updateCourse(@RequestBody Course course) {
        return Result.success().setData(courseService.updateCourse(course)).setCode(ResultCodeEnum.OK.getCode()).setMsg("修改课程成功");
    }

    @ApiOperation(value = "删除课程")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
    })
    @ResponseBody
    @PostMapping("/deleteCourse/{courseId}")
    public Result deleteCourseById(@PathVariable Integer courseId) {
        return Result.success().setData(courseService.deleteCourseById(courseId)).setCode(ResultCodeEnum.OK.getCode()).setMsg("删除课程成功");
    }
    @ApiOperation(value = "批量删除课程")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseIds", value="课程id"),
    })
    @ResponseBody
    @PostMapping("/deleteMultipleCourseById")
    public Result deleteMultipleCourseById(@RequestBody List<Integer> courseIds) {
        return Result.success().setData(courseService.deleteMultipleCourse(courseIds)).setCode(ResultCodeEnum.OK.getCode()).setMsg("删除课程成功");
    }
    @ApiOperation(value = "添加课程老师")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="courseName", value="课程名称"),
            @ApiImplicitParam(required = true,name="teacherId", value="操作老师Id"),
            @ApiImplicitParam(required = true,name="courseTeachers", value="一个老师数组包含teacherId,teacherName"),
    })
    @ResponseBody
    @PostMapping ("/insertCourseTeacher")
    public Result insertCourseTeacher(@RequestBody CourseTeacher courseTeacher) {
       Integer a=courseService.insertCourseTeacher(courseTeacher);
       if (a==0){
           return Result.failure(ResultCodeEnum.CREATED).setMsg("不是创建老师无权添加老师");
       }
        return Result.success().setData(a).setCode(ResultCodeEnum.OK.getCode()).setMsg("添加老师成功");
    }
    @ApiOperation(value = "通过课程id查找任课老师" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
    })
    @ResponseBody
    @GetMapping("/findCourseTeacherByCourseId/{courseId}")
    public Result getCourseTeacherByCourseId(@PathVariable Integer courseId) {
        List<CourseTeacher> courseTeachers=courseService.findCourseTeacherByCourseId(courseId);
        int Count = courseTeachers.size();
        return Result.success().setData(courseTeachers).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("根据课程id查找老师成功");
    }
    @ApiOperation(value = "通过课程id以及老师id退出课程" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程名称"),
            @ApiImplicitParam(required = true,name="teacherId", value="课程名称"),
    })
    @ResponseBody
    @PostMapping("/deleteCourseTeacher")
    public Result deleteCourseTeacherByCourseId(@RequestBody CourseTeacher courseTeacher) {
         Integer a =courseService.deleteCourseTeacher(courseTeacher);
        return Result.success().setData(a).setCode(ResultCodeEnum.OK.getCode()).setMsg("删除老师成功");
    }
    @ApiOperation(value = "通过教师id查看所教课程" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="teacherId", value="教师Id"),
    })
    @ResponseBody
    @GetMapping("/findCourseByTeacherId/{teacherId}")
    public Result getCourseByTeacherId(@PathVariable Integer teacherId) {
        List<CourseTeacher> courseTeachers=courseService.findCourseByTeacherId(teacherId);
        int Count = courseTeachers.size();
        return Result.success().setData(courseTeachers).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("根据教师id查找课程成功");
    }
    @ApiOperation(value = "老师添加学生")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="courseName", value="课程名称"),
            @ApiImplicitParam(required = true,name="courseStudents", value="一个学生数组包含studentId,studentName"),
    })
    @ResponseBody
    @PostMapping ("/insertCourseStudent")
    public Result insertCourseStudent(@RequestBody CourseStudent courseStudent) {
        Integer a=courseService.insertCourseStudent(courseStudent);
        return Result.success().setData(a).setCode(ResultCodeEnum.OK.getCode()).setMsg("添加学生成功");
    }
    @ApiOperation(value = "删除课程学生")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="studentId", value="学生id"),
    })
    @ResponseBody
    @PostMapping("/deleteCourseStudent")
    public Result deleteCourseStudent(@RequestBody CourseStudent courseStudent) {
        return Result.success().setData(courseService.deleteCourseStudent(courseStudent)).setCode(ResultCodeEnum.OK.getCode()).setMsg("删除学生成功");
    }
    @ApiOperation(value = "通过课程号查看所选学生" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
    })
    @ResponseBody
    @GetMapping("/findCourseStudentByCourseId/{courseId}")
    public Result getCourseStudentByCId(@PathVariable Integer courseId) {
        List<CourseStudent> courseStudents=courseService.findCourseStudentByCId(courseId);
        int Count = courseStudents.size();
        return Result.success().setData(courseStudents).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查找学生成功");
    }
    @ApiOperation(value = "老师给学生分组")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="teamId", value="团队id"),
            @ApiImplicitParam(required = true,name="courseStudents", value="一个学生数组包含studentId"),
    })
    @ResponseBody
    @PostMapping ("/setStudentGroup")
    public Result setStudentGroup(@RequestBody CourseStudent courseStudent) {
        return Result.success().setData(courseService.updateCourseStudent(courseStudent)).setCode(ResultCodeEnum.OK.getCode()).setMsg("添加学生分组成功");
    }
    @ApiOperation(value = "学生提交加入课程申请")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="courseName", value="课程名称"),
            @ApiImplicitParam(required = true,name="studentId", value="学生id"),
            @ApiImplicitParam(required = true,name="studentName", value="学生名称"),
    })
    @ResponseBody
    @PostMapping ("/insertApproveRequest")
    public Result insertApproveRequest(@RequestBody ApproveRequest approveRequest) {
        Integer a=courseService.insertApproveRequest(approveRequest);
        if (a==0){
           return Result.failure(ResultCodeEnum.CREATED).setMsg("已加入课程请勿重复提交");
        }
        if (a==2){
            return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("已有申请请勿重复提交");
        }
        return Result.success().setData(a).setCode(ResultCodeEnum.OK.getCode()).setMsg("添加请求成功");
    }
    @ApiOperation(value = "通过课程号查找所有加入课程请求" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
    })
    @ResponseBody
    @GetMapping("/findARByCourseId/{courseId}")
    public Result getARByCId(@PathVariable Integer courseId) {
        List<ApproveRequest> approveRequests=courseService.getRAByCourseId(courseId);
        int Count =approveRequests.size();
        return Result.success().setData(approveRequests).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查找请求成功");
    }
    @ApiOperation(value = "通过学生号查找所有自己提交的请求" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="studentId", value="学生Id"),
    })
    @ResponseBody
    @GetMapping("/findARByStudentId/{studentId}")
    public Result getARBySId(@PathVariable Integer studentId) {
        List<ApproveRequest> approveRequests=courseService.getRAByStudentId(studentId);
        int Count =approveRequests.size();
        return Result.success().setData(approveRequests).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查找请求成功");
    }
    @ApiOperation(value = "老师审核请求" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="courseName", value="课程名称"),
            @ApiImplicitParam(required = true,name="studentId", value="学生id"),
            @ApiImplicitParam(required = true,name="studentName", value="学生名称"),
            @ApiImplicitParam(required = true,name="isApproved", value="同意传2不同意传3"),
    })
    @ResponseBody
    @PostMapping ("/teacherCheckAR")
    public Result updateAR(@RequestBody ApproveRequest approveRequest) {
        return Result.success().setData(courseService.updateAR(approveRequest)).setCode(ResultCodeEnum.OK.getCode()).setMsg("审核完成");
    }
    @ApiOperation(value = "通过学生号查找已加入课程" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="studentId", value="学生Id"),
    })
    @ResponseBody
    @GetMapping("/findCourseBySId/{studentId}")
    public Result getCourseBySId(@PathVariable Integer studentId) {
        List<CourseStudent> courseStudents=courseService.findCourseByStudentId(studentId);
        int Count =courseStudents.size();
        return Result.success().setData(courseStudents).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查找请求成功");
    }
    @ApiOperation(value = "通过学生号查找未加入课程学生" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="studentId", value="学生Id"),
    })
    @ResponseBody
    @GetMapping("/findUnjoinCourseBySId/{studentId}")
    public Result getUnjoinCourseBySId(@PathVariable Integer studentId) {
        List<Course> courseStudents=courseService.findUnjoinCourseByStudentId(studentId);
        int Count =courseStudents.size();
        return Result.success().setData(courseStudents).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查找请求成功");
    }


}
