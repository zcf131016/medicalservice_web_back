package com.example.medicalservice.control;

import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.service.StudentFileService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
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

    @ApiOperation(value="获取学生上传的文件")
    @GetMapping("/getFileById/{caseId}/{studentId}")
    public Result getFileById(@PathVariable("caseId") Integer caseId, @PathVariable("studentId") Integer studentId) {
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setData(studentFileService.getFileByStudentId(caseId,studentId)).setMsg("获取成功");
    }

    @ApiOperation(value="上传文件")
    @RequestMapping(value="/uploadFile", produces = {"application/pdf;charset=UTF-8","application/zip;charset=UTF-8","application/msword;charset=UTF-8"})
    public Result uploadFile(@RequestParam("file") MultipartFile file,
                             @RequestParam("caseId") Integer caseId,
                             @RequestParam("studentId") Integer studentId,
                             HttpServletRequest request) {
        if (!file.isEmpty()) {
            // 获取文件名
            String fileName = file.getOriginalFilename();
            fileName = String.valueOf(caseId) + "_" + String.valueOf(studentId) + fileName;
            // 获取后缀名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));
            // 文件存放路径
            String filePath = baseConfig.getStudentFilePath() + fileName;
            File dest = new File(filePath);
            // 检查文件是否存在
            if(!dest.getParentFile().exists()) {
                dest.getParentFile().mkdirs();
            }
            try {
                file.transferTo(dest);
                StudentFile studentFile = new StudentFile();
                studentFile.setCaseId(caseId);
                studentFile.setStudentId(studentId);
                studentFile.setFilePath(filePath);
                studentFile.setFileType(suffixName);
                studentFileService.uploadFile(studentFile);
                return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("上传成功");
            } catch (IllegalStateException | IOException e) {
                e.printStackTrace();
            }
        }
        return Result.failure(ResultCodeEnum.UPLOAD_FAILED).setMsg("上传失败");
    }
}
