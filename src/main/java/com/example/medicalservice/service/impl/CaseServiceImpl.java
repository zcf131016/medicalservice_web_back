package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.CaseFile;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.domain.Cases;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.mapper.CasesMapper;
import com.example.medicalservice.service.CaseService;
import com.example.medicalservice.util.RandomUtil;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaseServiceImpl implements CaseService {
    @Autowired
    CasesMapper casesMapper;

    @Override
    public List<Cases> getAllCases() {
        return casesMapper.getAllCases();
    }

    @Override
    public int getAllCasesCount() {
        return casesMapper.getAllCasesCount();
    }

    @Override
    public List<Cases> getCasesbyPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cases> casesList = casesMapper.getAllCases();
        return casesList;
    }

    @Override
    public List<Cases> getcasesByCourseId(Integer courseId, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cases> casesList = casesMapper.getcasesByCourseId(courseId);
        return casesList;
    }

    @Override
    public int getcasesCountByCourseId(Integer courseId) {
        return casesMapper.getcasesCountByCourseId(courseId);
    }

    @Override
    public List<Cases> getcasesBycaseName(String caseName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cases> casesList = casesMapper.getcasesBycaseName(caseName);
        return casesList;
    }

    @Override
    public int getcasesCountBycaseName(String caseName) {
        return casesMapper.getcasesCountBycaseName(caseName);
    }

    @Override
    public List<Cases> getcasesByteacherId(Integer creatTeacher, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Cases> casesList = casesMapper.getcasesByteacherId(creatTeacher);
        return casesList;
    }

    @Override
    public int getcasesCountByteacherId(Integer creatTeacher) {
        return casesMapper.getcasesCountByteacherId(creatTeacher);
    }

    @Override
    public Cases getcasebyId(Integer caseId) {
        return casesMapper.getcasebyId(caseId);
    }

    @Override
    public int insertCases(Cases cases) {
        if (cases.getCaseName() == null) {
            throw new UserFriendException("案例名称不能为空!", ResultCodeEnum.PARAMS_MISS.getCode());
        } else if (cases.getCourseId() == 0) {
            throw new UserFriendException("课程id不能为空!", ResultCodeEnum.PARAMS_MISS.getCode());
        } else if (cases.getCreatTeacher() == null) {
            throw new UserFriendException("创建老师不能为空!", ResultCodeEnum.PARAMS_MISS.getCode());
        }
        cases.setCaseId(RandomUtil.getRandom(9));//生成9位的caseid
        casesMapper.insertCases(cases);
        return cases.getCaseId();//返回随机生成的caseId
    }

    @Override
    public int deleteCasesByid(Integer caseId) {
        return casesMapper.deleteCasesByid(caseId);
    }

    @Override
    public int updateCases(Cases cases) {
        if (cases.getCaseName() == null) {
            throw new UserFriendException("案例名称不能为空!", ResultCodeEnum.PARAMS_MISS.getCode());
        }
        return casesMapper.updateCases(cases);
    }

    @Override
    public CaseImage getcaseimagebymainId(Integer id) {
        return casesMapper.getcaseimagebymainId(id);
    }

    @Override
    public List<CaseImage> getcaseimagebyId(Integer caseId) {
        return casesMapper.getcaseimagebyId(caseId);
    }

    @Override
    public int insertCasesImage(CaseImage caseImage) {
        casesMapper.insertCasesImage(caseImage);
        return caseImage.getId();
    }

    @Override
    public int deletecasesImageByid(Integer id) {
        return casesMapper.deletecasesImageByid(id);
    }

    @Override
    public List<CaseFile> getcasefilebyId(Integer caseId) {
        return casesMapper.getcasefilebyId(caseId);
    }

    @Override
    public int getcasefileCountbyId(Integer caseId){ return casesMapper.getcasefileCountbyId(caseId);}

    public CaseFile downloadcasefilebyId(Integer id) {
        return casesMapper.downloadcasefilebyId(id);
    }

    @Override
    public int insertCasesFile(CaseFile caseFile) {
        return casesMapper.insertCasesFile(caseFile);
    }

    @Override
    public int deletecasesFileByid(Integer id){
        return casesMapper.deletecasesFileByid(id);
    }

    @Override
    public int deletecasesFileByCaseid(Integer caseId){
        return casesMapper.deletecasesFileByCaseid(caseId);
    }
}
