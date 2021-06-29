package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.StudentFile;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.mapper.StudentFileMapper;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.service.StudentFileService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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

    @Autowired
    UserMapper userMapper;

    @Override
    public List<StudentFile> getFileByStudentId(Integer caseId, Integer studentId) {
        return studentFileMapper.getFileByStudentId(caseId, studentId);
    }

    @Override
    public void uploadFile(StudentFile studentFile) {
        studentFileMapper.addFile(studentFile);
    }

    @Override
    public StudentFile getFileById(Integer id) {
        return studentFileMapper.getFileById(id);
    }

    @Override
    public PageInfo getFileByCaseId(Integer caseId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<StudentFile> list = studentFileMapper.getFileByCaseId(caseId);
        if(list.size() == 0) throw new UserFriendException();
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    @Override
    public void deleteFileById(Integer id) {
        studentFileMapper.deleteFileById(id);
    }

    @Override
    public List<StudentFile> getFileByStudentName(String studentName) {
        List<StudentFile> list = studentFileMapper.getFileByStudentName(studentName);
        for(int i = 0;i < list.size();++i) {
            User user = userMapper.getUserByUserId(list.get(i).getStudentId());
            list.get(i).setStudentName(user.getRealName());
        }
        return list;
    }
}
