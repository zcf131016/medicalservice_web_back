package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.domain.StudentFileDto;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.FileService;
import com.example.medicalservice.service.StudentFileService;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/22 17:27
 */
@Api(tags = "学生文件接口")
@RestController
@RequiresAuthentication
@RequestMapping("/sfile")
public class StudentFileController {

    @Autowired
    BaseConfig baseConfig;

    @Autowired
    StudentFileService studentFileService;

    @Autowired
    FileService fileService;

    @Autowired
    UserService userService;



    @ApiOperation(value="获取学生上传的文件列表")
    @GetMapping("/getFileById/{caseId}/{studentId}")
    public Result getFileById(@PathVariable("caseId") Integer caseId,
                              @PathVariable("studentId") Integer studentId,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setData(studentFileService.getFileByStudentId(caseId,studentId)).setMsg("获取成功");
    }

    @ApiOperation(value = "根据caseId获取该case下所有学生的上传文件")
    @GetMapping("/getFileByCaseId/{caseId}/{pageNum}/{pageSize}")
    public Result getFileByCaseId(@PathVariable("caseId") Integer caseId,
                                  @PathVariable("pageNum") Integer pageNum,
                                  @PathVariable("pageSize") Integer pageSize) {
        JSONObject json = new JSONObject();
        try {
            PageInfo<StudentFile> pageInfo = studentFileService.getFileByCaseId(caseId,pageNum,pageSize);
            json.put("pageInfo",pageInfo);
        } catch (UserFriendException e) {
            e.printStackTrace();
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("没有数据");
        }
        return Result.success().setData(json.get("pageInfo"));
    }

    @ApiOperation(value="上传文件")
    @RequestMapping(value="/uploadFile", produces = {"application/json;charset=UTF-8","application/pdf;charset=UTF-8","application/zip;charset=UTF-8","application/msword;charset=UTF-8"},method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("caseId") Integer caseId,
                             @RequestParam("studentId") Integer studentId,
                             HttpServletRequest request) {
        String prefix = String.valueOf(caseId) + "_" + String.valueOf(studentId) + "_";
        boolean result =  fileService.uploadFile(file,prefix,baseConfig.getStudentFilePath());
        if(result) {
            StudentFile studentFile = new StudentFile();
            studentFile.setCaseId(caseId);
            studentFile.setStudentId(studentId);
            studentFile.setFileType(file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".")));
            studentFile.setFilePath(baseConfig.getStudentFilePath() + prefix + file.getOriginalFilename());
            studentFileService.uploadFile(studentFile);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("上传成功");
        }
        return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
    }

    @ApiOperation(value="下载文件")
    @RequestMapping(value = "/downloadFile/{fileId}", produces = {"application/json;charset=UTF-8","application/octet-stream;charset=UTF-8"},method = RequestMethod.POST)
    public Result downloadFileByFileId(@PathVariable("fileId") Integer fileId,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        StudentFile studentFile = studentFileService.getFileById(fileId);
        if(studentFile == null ) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
        String filePath = studentFile.getFilePath();
        File newFile = new File(filePath);
        if(!newFile.exists()) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("对应路径的文件不存在");
        String fileType = studentFile.getFileType().substring(1);
        response.setHeader("Content-Disposition", "attachment;filename=" + filePath.substring(filePath.lastIndexOf("/")+1));
        response.setContentType("application/octet-stream;charset=UTF-8");
        // 获取文件
        boolean result = fileService.downloadFile(filePath, response);
        if(result) return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("下载成功");
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("下载失败");
    }

    @ApiOperation(value="根据文件id删除文件")
    @DeleteMapping("/deleteFile/{fileId}")
    public Result deleteFile(@PathVariable("fileId") Integer fileId) {
        StudentFile studentFile = studentFileService.getFileById(fileId);
        if(studentFile == null) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
        boolean result = fileService.deleteFile(studentFile.getFilePath());
        if(result) {
            studentFileService.deleteFileById(fileId);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除成功");
        }
        return Result.failure(ResultCodeEnum.DELETE_FAILED).setMsg("删除失败");
    }

    @ApiOperation(value="批量删除学生文件, 接收文件id的列表")
    @DeleteMapping("/deleteBatchFiles")
    public Result deleteBatchFile(@RequestBody StudentFileDto files) {
        Integer count = 0;
        for(Integer file : files.getFileIds()) {
            StudentFile studentFile = studentFileService.getFileById(file);
            if(studentFile != null && fileService.deleteFile(studentFile.getFilePath())) {
                studentFileService.deleteFileById(file);
                count++;
            }
        }
        return Result.success().setCode(ResultCodeEnum.DELETED.getCode()).setMsg("共删除了 " + count + " 个文件" + (count==files.getFileIds().size() ? "!" : "，" + (files.getFileIds().size()-count) +"个文件不存在！"));
    }

    @ApiOperation(value="根据真实姓名模糊搜索",notes = "只需要传递realName字段")
    @GetMapping("/getFileByName")
    public Result getFileByName(@RequestBody User user) {
        String studentName = user.getRealName();
        List<StudentFile> files = studentFileService.getFileByStudentName(studentName);
        return Result.success().setData(files).setMsg("查询成功").setCount(files.size());
    }
}
