package com.example.medicalservice.service;

import com.example.medicalservice.domain.StudentFile;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/22 17:20
 */
public interface StudentFileService {
    List<StudentFile> getFileByStudentId(Integer caseId, Integer studentId);
    void uploadFile(StudentFile studentFile);
    StudentFile getFileById(Integer id);
}
