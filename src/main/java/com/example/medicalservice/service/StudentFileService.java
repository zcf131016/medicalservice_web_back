package com.example.medicalservice.service;

import com.example.medicalservice.domain.StudentFile;
import com.github.pagehelper.PageInfo;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/22 17:20
 */
public interface StudentFileService {
    List<StudentFile> getFileByStudentId(Integer caseId, Integer studentId);
    void uploadFile(StudentFile studentFile);
    StudentFile getFileById(Integer id);
    PageInfo getFileByCaseId(Integer caseId,Integer pageNum, Integer pageSize);
    void deleteFileById(Integer id);
    List<StudentFile> getFileByStudentName(String studentName);
}
