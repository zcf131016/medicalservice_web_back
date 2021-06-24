package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.StudentFile;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/22 17:02
 */
@Mapper
public interface StudentFileMapper {
    List<StudentFile> getFileByStudentId(Integer caseId,Integer studentId);
    void addFile(StudentFile studentFile);
    StudentFile getFileById(Integer id);
    void deleteFileById(Integer id);
    List<StudentFile> getFileByCaseId(Integer caseId);
}
