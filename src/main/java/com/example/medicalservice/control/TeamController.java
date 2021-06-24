package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.domain.CourseStudent;
import com.example.medicalservice.service.CourseService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "团队管理")
@RestController
@RequestMapping("/team")
public class TeamController {
    @Autowired
    CourseService courseService;
    @ApiOperation(value = "分页查看已分组学生名单" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
            @ApiImplicitParam(required = true,name="pageNum", value="页数"),
            @ApiImplicitParam(required = true,name="pageSize", value="页面大小"),
    })
    @ResponseBody
    @GetMapping("/findInTeamStudentByCourseId/{courseId}/{pageNum}/{pageSize}")
    public Result getInTeamStudentByCId(@PathVariable Integer courseId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        JSONObject json = new JSONObject();
        PageInfo pageInfo =courseService.findInTeamStudentByCourseId(courseId,pageNum,pageSize);
        json.put("pageInfo", pageInfo);
        return Result.success().setData(pageInfo).setCode(ResultCodeEnum.OK.getCode()).setMsg("查找学生成功");
    }
    @ApiOperation(value = "删除整个学生分组" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
            @ApiImplicitParam(required = true,name="teamId", value="小组Id"),
    })
    @ResponseBody
    @PostMapping("/deleteTeamByCIdSId/{courseId}/{teamId}")
    public Result deleteTeamByCIdSId(@PathVariable Integer courseId,@PathVariable Integer teamId) {
        return Result.success().setData(courseService.deleteTeamByCIdTId(courseId,teamId)).setCode(ResultCodeEnum.OK.getCode()).setMsg("删除整组学生成功");
    }
    @ApiOperation(value = "删除分组里的单个学生" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
            @ApiImplicitParam(required = true,name="studentId", value="学生Id"),
            @ApiImplicitParam(required = true,name="teamId", value="团队Id"),
    })
    @ResponseBody
    @PostMapping("/deleteOneStudentFromTeam/{courseId}/{teamId}/{studentId}")
    public Result deleteOneStudentFromTeam(@PathVariable Integer courseId, @PathVariable Integer teamId,@PathVariable Integer studentId) {
        return Result.success().setData(courseService.deleteOneStudentById(courseId,teamId,studentId)).setCode(ResultCodeEnum.OK.getCode()).setMsg("删除单个学生成功");
    }
    @ApiOperation(value = "查找团队成员" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程id"),
            @ApiImplicitParam(required = true,name="teamId", value="小组id"),
    })
    @ResponseBody
    @GetMapping("/findTeamStudent/{courseId}/{teamId}")
    public Result findTeamStudent(@PathVariable Integer courseId,@PathVariable Integer teamId) {
        List<CourseStudent> courseStudents=courseService.findTeamStudent(courseId,teamId);
        int Count = courseStudents.size();
        if (Count==0){
            return Result.failure(ResultCodeEnum.CREATED).setMsg("团里无人");
        }
        return Result.success().setData(courseStudents).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("根据课程名查找课程成功");
    }
    @ApiOperation(value = "更新分组学生" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
            @ApiImplicitParam(required = true,name="studentId", value="学生Id"),
            @ApiImplicitParam(required = true,name="teamId", value="团队Id"),
    })
    @ResponseBody
    @PostMapping("/updateOneStudentFromTeam/{courseId}/{teamId}/{studentId}")
    public Result updateOneStudentFromTeam(@PathVariable Integer courseId, @PathVariable Integer teamId,@PathVariable Integer studentId) {
        return Result.success().setData(courseService.updateOneStudentById(courseId,teamId,studentId)).setCode(ResultCodeEnum.OK.getCode()).setMsg("更新分组成功");
    }
    @ApiOperation(value = "查看课程未分组学生" )
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="courseId", value="课程Id"),
    })
    @ResponseBody
    @GetMapping("/findNotTeamStudentByCourseId/{courseId}")
    public Result getNotTeamStudentByCId(@PathVariable Integer courseId) {
        List<CourseStudent> courseStudents=courseService.findNotTeamStudentByCId(courseId);
        int Count = courseStudents.size();
        return Result.success().setData(courseStudents).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查找学生成功");
    }
}
