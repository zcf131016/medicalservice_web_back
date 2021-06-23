package com.example.medicalservice.control;

import com.example.medicalservice.domain.CaseFile;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.domain.Cases;
import com.example.medicalservice.service.CaseService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zx
 * @date 2021/6/23 9:06
 */

@Api(tags = "案例管理")
@RestController
@CrossOrigin
@RequestMapping("/case")
public class CaseController {
    @Autowired
    CaseService caseService;

    @RequiresRoles(value={"teacher","admin"},logical= Logical.OR)
    @ApiOperation(value = "查找所有案例")
    @GetMapping("/findAllCases")
    public Result findAllCases(){
        List<Cases> casesList= caseService.getAllCases();
        int Count=casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有案例成功");
    }
    @ApiOperation(value = "分页查找所有案例")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name = "pageNum",value = "当前页数"),
            @ApiImplicitParam(required = true,name = "pageSize",value = "每页显示条数")
    })
    @PostMapping("/findCasesByPage/{pageNum}/{pageSize}")
    public Result findCasesByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize){
        List<Cases> casesList= caseService.getCasesbyPage(pageNum,pageSize);
        int count=casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("分页查找案例成功");
    }
    @ApiOperation(value = "根据courseId查找所有案例")
    @ApiParam(name="courseId",type = "Integer")
    @GetMapping("/findCasesbycourseId/{courseId}")
    public Result findCasesbycourseId(@PathVariable Integer courseId){
        List<Cases> casesList= caseService.getcasesByCourseId(courseId);
        int count=casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }
    @ApiOperation(value = "根据案例名称查找案例")
    @ApiParam(name="caseName",type = "String")
    @GetMapping("/findCasesbycaseName/{caseName}")
    public Result findCasesbycaseName(@PathVariable String caseName){
        List<Cases> casesList=caseService.getcasesBycaseName(caseName);
        int count=casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }
    @ApiOperation(value = "根据teacherId查找所有案例")
    @ApiParam(name="creatTeacher",type = "Integer")
    @GetMapping("/findCasesbyteacherId/{creatTeacher}")
    public Result findCasesbyteacherId(@PathVariable Integer creatTeacher){
        List<Cases> casesList=caseService.getcasesByteacherId(creatTeacher);
        int count=casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }
    @ApiOperation(value = "根据caseId查找案例")
    @ApiParam(name="caseId",type = "Integer")
    @GetMapping("/findCasesbycaseId/{caseId}")
    public Result findCasesbycaseId(@PathVariable Integer caseId){
        Cases cases=caseService.getcasebyId(caseId);
        List<CaseImage> caseImageList=caseService.getcaseimagebyId(caseId);
        List<CaseFile> caseFileList=caseService.getcasefilebyId(caseId);
        cases.setCaseImages(caseImageList);
        cases.setCaseFiles(caseFileList);
        return Result.success().setData(cases).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取案例成功!");
    }
    @ApiOperation(value = "新增案例")
    @PostMapping("/insertcase")
    public Result insertcase(@RequestBody Cases cases){
        if(cases.getIsPublish()==0){
            cases.setIsPublish(1);//若为设置是否发布，默认设置为未发布
        }
        caseService.insertCases(cases);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("新增案例成功！");
    }
    @ApiOperation(value = "根据caseId删除案例")
    @DeleteMapping("/deletecase/{caseId}")
    public Result deletecase(@PathVariable Integer caseId){//存入路径里的图片和文件也要删除，这里暂未实现
        caseService.deleteCasesByid(caseId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }
    @ApiOperation(value = "批量删除案例",notes = "前端提供名为caseIdList的Integer数组表示需要删除的案例id号")
    @DeleteMapping("/batchdeletecase")
    public Result batchdeletecase(@RequestBody List<Integer> caseIdList){
        for (int i = 0; i < caseIdList.size(); i++) {
            caseService.deleteCasesByid(caseIdList.get(i));
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }
    @ApiOperation(value = "修改案例、发布案例")
    @PutMapping("/updatecase")
    public Result updatecase(@RequestBody Cases cases){
        caseService.updateCases(cases);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }
}
