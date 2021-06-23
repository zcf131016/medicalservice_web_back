package com.example.medicalservice.config;

import org.springframework.context.annotation.Configuration;

/**
 * @author Lin YuHang
 * @date 2021/6/22 20:23
 */
@Configuration
public class BaseConfig {
    private String studentFilePath = "./studentFile/";
    private String caseFilePath = "./caseFile/";

    public String getStudentFilePath() {
        return studentFilePath;
    }

    public void setStudentFilePath(String studentFilePath) {
        this.studentFilePath = studentFilePath;
    }

    public String getCaseFilePath() {
        return caseFilePath;
    }

    public void setCaseFilePath(String caseFilePath) {
        this.caseFilePath = caseFilePath;
    }
}
