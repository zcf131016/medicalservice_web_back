package com.example.medicalservice.control;

import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.service.FileService;
import com.example.medicalservice.service.StudentFileService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;

/**
 * @author Lin YuHang
 * @date 2021/6/22 17:27
 */
@RestController
@RequestMapping("/sfile")
public class StudentFileController {

    @Autowired
    BaseConfig baseConfig;

    @Autowired
    StudentFileService studentFileService;

    @Autowired
    FileService fileService;

    @ApiOperation(value="获取学生上传的文件列表")
    @GetMapping("/getFileById/{caseId}/{studentId}")
    public Result getFileById(@PathVariable("caseId") Integer caseId,
                              @PathVariable("studentId") Integer studentId,
                              HttpServletRequest request,
                              HttpServletResponse response) {
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setData(studentFileService.getFileByStudentId(caseId,studentId)).setMsg("获取成功");
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
    @RequestMapping(value = "/downloadFile/{fileId}", produces = {"application/json;charset=UTF-8","application/pdf;charset=UTF-8","application/zip;charset=UTF-8","application/msword;charset=UTF-8"},method = RequestMethod.POST)
    public Result downloadFileByFileId(@PathVariable("fileId") Integer fileId,
                                       HttpServletRequest request,
                                       HttpServletResponse response) {
        StudentFile studentFile = studentFileService.getFileById(fileId);
        if(studentFile == null ) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("文件不存在");
        String filePath = studentFile.getFilePath();
        File newFile = new File(filePath);
        if(!newFile.exists()) return Result.failure(ResultCodeEnum.INQUIRE_FAILED).setMsg("对应路径的文件不存在");
        response.setHeader("Content-Disposition", "attachment;filename=" + filePath.substring(filePath.lastIndexOf("/")));
        response.setContentType("application/octet-stream");
        // 获取文件
        boolean result = fileService.downloadFile(filePath, response);
        if(result) return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("下载成功");
        return Result.failure(ResultCodeEnum.NOT_IMPLEMENTED).setMsg("下载失败");
    }
}
