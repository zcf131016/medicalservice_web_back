package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;
//import jdk.jfr.DataAmount;
import lombok.Data;

import java.sql.Date;
import java.util.Arrays;

@ApiModel
@Data
public class CaseImage {
    private Integer id;
    private Integer caseId;
    private Object image;
    private Date creatTime;
    private String imageName;




}
