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
    public List<Cases> getcasesBycaseName(String caseName) {
        return casesMapper.getcasesBycaseName(caseName);
    }

    @Override
    public List<Cases> getcasesByteacherId(Integer creatTeacher) {
        return casesMapper.getcasesByteacherId(creatTeacher);
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
        return casesMapper.insertCases(cases);
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
    public List<CaseImage> getcaseimagebyId(Integer caseId) {
        return casesMapper.getcaseimagebyId(caseId);
    }

    @Override
    public int insertCasesImage(CaseImage caseImage) {
        return casesMapper.insertCasesImage(caseImage);
    }

    @Override
    public List<CaseFile> getcasefilebyId(Integer caseId) {
        return casesMapper.getcasefilebyId(caseId);
    }

    public CaseFile downloadcasefilebyId(Integer id) {
        return casesMapper.downloadcasefilebyId(id);
    }

    @Override
    public int insertCasesFile(CaseFile caseFile) {
        return casesMapper.insertCasesFile(caseFile);
    }
}
