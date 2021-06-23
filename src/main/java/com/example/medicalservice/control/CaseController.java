package com.example.medicalservice.control;

import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.CaseFile;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.domain.Cases;
import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.service.CaseService;
import com.example.medicalservice.service.FileService;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
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
    @Autowired
    BaseConfig baseConfig;
    @Autowired
    FileService fileService;
    @Autowired
    UserService userService;

    @RequiresRoles(value = {"teacher", "admin"}, logical = Logical.OR)
    @ApiOperation(value = "查找所有案例")
    @GetMapping("/findAllCases")
    public Result findAllCases() {
        List<Cases> casesList = caseService.getAllCases();
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int Count = casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有案例成功");
    }

    @ApiOperation(value = "分页查找所有案例")
    @ApiImplicitParams({@ApiImplicitParam(required = true, name = "pageNum", value = "当前页数"),
            @ApiImplicitParam(required = true, name = "pageSize", value = "每页显示条数")
    })
    @PostMapping("/findCasesByPage/{pageNum}/{pageSize}")
    public Result findCasesByPage(@PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Cases> casesList = caseService.getCasesbyPage(pageNum, pageSize);
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int count = casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("分页查找案例成功");
    }

    //    @ApiOperation(value = "根据courseId查找所有案例")
//    @ApiParam(name="courseId",type = "Integer")
//    @GetMapping("/findCasesbycourseId/{courseId}")
//    public Result findCasesbycourseId(@PathVariable Integer courseId){
//        List<Cases> casesList= caseService.getcasesByCourseId(courseId);
//        int count=casesList.size();
//        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
//    }
    @ApiOperation(value = "根据courseId查找所有案例并分页")
    @ApiParam(name = "courseId", type = "Integer")
    @GetMapping("/findCasesbycourseId/{courseId}/{pageNum}/{pageSize}")
    public Result findCasesbycourseId(@PathVariable Integer courseId, @PathVariable Integer pageNum, @PathVariable Integer pageSize) {
        List<Cases> casesList = caseService.getcasesByCourseId(courseId, pageNum, pageSize);
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int count = casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }

    @ApiOperation(value = "根据案例名称查找案例")
    @ApiParam(name = "caseName", type = "String")
    @GetMapping("/findCasesbycaseName/{caseName}")
    public Result findCasesbycaseName(@PathVariable String caseName) {
        List<Cases> casesList = caseService.getcasesBycaseName(caseName);
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(userService.getUserByUserId(casesList.get(i).getCreatTeacher()).getRealName());
        }
        int count = casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }

    @ApiOperation(value = "根据teacherId查找所有案例")
    @ApiParam(name = "creatTeacher", type = "Integer")
    @GetMapping("/findCasesbyteacherId/{creatTeacher}")
    public Result findCasesbyteacherId(@PathVariable Integer creatTeacher) {
        List<Cases> casesList = caseService.getcasesByteacherId(creatTeacher);
        String teaName=userService.getUserByUserId(creatTeacher).getRealName();
        for (int i = 0; i < casesList.size(); i++) {
            casesList.get(i).setTeacherName(teaName);
        }
        int count = casesList.size();
        return Result.success().setData(casesList).setCode(ResultCodeEnum.OK.getCode()).setCount(count).setMsg("查询案例成功");
    }

    @ApiOperation(value = "根据caseId查找案例")
    @ApiParam(name = "caseId", type = "Integer")
    @GetMapping("/findCasesbycaseId/{caseId}")
    public Result findCasesbycaseId(@PathVariable Integer caseId) {
        Cases cases = caseService.getcasebyId(caseId);
        cases.setTeacherName(userService.getUserByUserId(cases.getCreatTeacher()).getRealName());
        List<CaseImage> caseImageList = caseService.getcaseimagebyId(caseId);
        List<CaseFile> caseFileList = caseService.getcasefilebyId(caseId);
        cases.setCaseImages(caseImageList);
        cases.setCaseFiles(caseFileList);
        return Result.success().setData(cases).setCode(ResultCodeEnum.OK.getCode()).setMsg("获取案例成功!");
    }

    @ApiOperation(value = "新增案例")
    @PostMapping("/insertcase")
    public Result insertcase(@RequestBody Cases cases) {
        if (cases.getIsPublish() == 0) {
            cases.setIsPublish(1);//若为设置是否发布，默认设置为未发布
        }
        caseService.insertCases(cases);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("新增案例成功！");
    }

    @ApiOperation(value = "根据caseId删除案例")
    @DeleteMapping("/deletecase/{caseId}")
    public Result deletecase(@PathVariable Integer caseId) {//存入路径里的图片和文件也要删除，这里暂未实现
        caseService.deleteCasesByid(caseId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }

    @ApiOperation(value = "批量删除案例", notes = "前端提供名为caseIdList的Integer数组表示需要删除的案例id号")
    @DeleteMapping("/batchdeletecase")
    public Result batchdeletecase(@RequestBody List<Integer> caseIdList) {
        for (int i = 0; i < caseIdList.size(); i++) {
            caseService.deleteCasesByid(caseIdList.get(i));
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }

    @ApiOperation(value = "修改案例、发布案例")
    @PutMapping("/updatecase")
    public Result updatecase(@RequestBody Cases cases) {
        caseService.updateCases(cases);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除案例成功！");
    }

    @ApiOperation(value = "上传文件（治疗或诊断方案）到指定案例")
    @RequestMapping(value = "/uploadFiletoCases", produces = {"application/json;charset=UTF-8", "application/pdf;charset=UTF-8", "application/zip;charset=UTF-8", "application/msword;charset=UTF-8", "application/vnd.ms-powerpoint;charset=UTF-8", "image/png;charset=UTF-8", "image/jpeg;charset=UTF-8", "application/rar;charset=UTF-8"}, method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("caseId") Integer caseId,
                             HttpServletRequest request) {
        String prefix = String.valueOf(caseId) + "_";
        boolean result = fileService.uploadFile(file, prefix, baseConfig.getStudentFilePath());
        if (result) {
            CaseFile caseFile = new CaseFile();
            caseFile.setCaseId(caseId);
            caseFile.setFileUrl(baseConfig.getCaseFilePath() + prefix + file.getOriginalFilename());
            caseService.insertCasesFile(caseFile);
//            StudentFile studentFile = new StudentFile();
//            studentFile.setCaseId(caseId);
            //studentFile.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
//            studentFile.setFilePath(baseConfig.getStudentFilePath() + prefix + file.getOriginalFilename());
//            studentFileService.uploadFile(studentFile);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("上传成功");
        }
        return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
    }

    @ApiOperation(value = "在案例中下载文件")
    @RequestMapping(value = "/downloadFileFromCasesbyid/{id}", produces = {"application/json;charset=UTF-8", "application/pdf;charset=UTF-8", "application/zip;charset=UTF-8", "application/msword;charset=UTF-8", "application/vnd.ms-powerpoint;charset=UTF-8", "image/png;charset=UTF-8", "image/jpeg;charset=UTF-8", "application/rar;charset=UTF-8"}, method = RequestMethod.POST)
    public Result downloadFileFromCasesbyid(@PathVariable("id") Integer id,
                                            HttpServletRequest request,
                                            HttpServletResponse response) {
        CaseFile caseFile = caseService.downloadcasefilebyId(id);
        if (caseFile == null)
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
        String filePath = caseFile.getFileUrl();
        File newFile = new File(filePath);
        if (!newFile.exists())
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("对应路径的文件不存在");
//        StudentFile studentFile = studentFileService.getFileById(fileId);
//        if(studentFile == null ) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
//        String filePath = studentFile.getFilePath();
//        File newFile = new File(filePath);
//        if(!newFile.exists()) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("对应路径的文件不存在");
        response.setHeader("Content-Disposition", "attachment;filename=" + filePath.substring(filePath.lastIndexOf("/")));
        response.setContentType("application/octet-stream");
        // 获取文件
        boolean result = fileService.downloadFile(filePath, response);
        if (result)
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("下载成功");
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("下载失败");
    }

}
