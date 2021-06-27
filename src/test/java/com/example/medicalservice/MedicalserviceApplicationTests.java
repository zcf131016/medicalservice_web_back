package com.example.medicalservice;

import com.example.medicalservice.domain.CaseFile;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.domain.Cases;
import com.example.medicalservice.mapper.CasesMapper;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.util.RandomUtil;
import org.apache.ibatis.annotations.Case;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@SpringBootTest
class MedicalserviceApplicationTests {

    @Autowired
    CasesMapper casesMapper;
    UserMapper userMapper;
    @Test
    void contextLoads() {
    }
    @Test
    void test(){
        CaseImage caseImage=casesMapper.getcaseimagebymainId(9);
        System.out.println(caseImage.getCreatTime());
//        Cases cases=new Cases();
//        cases.setCaseId(987654321);
//        cases.setCaseName("zx的测试");
//        cases.setCourseId(10000);
//        Date date =new Date();
//        cases.setCreatTime(date);
//        cases.setCreatTeacher(1);
//        cases.setMedHistory("最近没吃药");
//        cases.setIsPublish(1);
//        casesMapper.insertCases(cases);
//        CaseImage caseImage=new CaseImage();
//        caseImage;
//        caseImage.setImageUrl("./image/img2.png");
//        List<Cases> casesList=casesMapper.getcasesByCourseId(10000);
//        for (int i = 0; i < casesList.size(); i++) {
//            System.out.println(casesList.get(i));
//        }
        //List<CaseImage> caseImage=casesMapper.getcaseimagebyId(1000);
        //System.out.println(userMapper.findAllUser());
    }

}
