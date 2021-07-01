package com.example.medicalservice.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Lin YuHang
 * @date 2021/6/22 20:23
 */
@Configuration
public class BaseConfig {
    private String studentFilePath = "studentFile/";
    private String caseFilePath = "caseFile/";
    private String emailTemplate = "classpath:Template.html";

    public String getStudentFilePath() {
        return studentFilePath;
    }


    public String getCaseFilePath() {
        return caseFilePath;
    }

    public String getEmailTemplate() {
        return emailTemplate;
    }
}
