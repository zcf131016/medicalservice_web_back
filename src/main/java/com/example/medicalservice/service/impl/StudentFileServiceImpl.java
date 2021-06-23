package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.mapper.StudentFileMapper;
import com.example.medicalservice.service.StudentFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/22 17:26
 */
@Service
public class StudentFileServiceImpl implements StudentFileService {

    @Autowired
    StudentFileMapper studentFileMapper;

    @Override
    public List<StudentFile> getFileByStudentId(Integer caseId, Integer studentId) {
        return studentFileMapper.getFileByStudentId(caseId, studentId);
    }

    @Override
    public void uploadFile(StudentFile studentFile) {
        studentFileMapper.addFile(studentFile);
    }
}
