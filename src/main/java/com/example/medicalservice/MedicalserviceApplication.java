package com.example.medicalservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class MedicalserviceApplication {

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation("/root/medicalSystem"); // 设置根目录
        return factory.createMultipartConfig();
    }

    public static void main(String[] args) {
        SpringApplication.run(MedicalserviceApplication.class, args);
    }

}
