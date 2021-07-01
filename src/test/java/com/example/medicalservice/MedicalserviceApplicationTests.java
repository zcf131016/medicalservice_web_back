package com.example.medicalservice;

import com.example.medicalservice.config.BaseConfig;
import com.example.medicalservice.domain.CaseImage;
import com.example.medicalservice.mapper.CasesMapper;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.security.mail.MailService;
import com.example.medicalservice.security.shiro.IPasswordEncoder;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;


@SpringBootTest
class MedicalserviceApplicationTests {

    @Autowired
    CasesMapper casesMapper;
    UserMapper userMapper;
    @Autowired
    IPasswordEncoder iPasswordEncoder;
    @Autowired
    MailService mailService;

    @Autowired
    BaseConfig baseConfig;

    @Test
    void contextLoads() throws NoSuchAlgorithmException, MessagingException, IOException {
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
