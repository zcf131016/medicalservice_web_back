package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
@ApiModel
public class DictionaryDetail {
    private  Integer id;
    private String typeCode;
    private String name;
    private Integer value;
    private Date creatTime;

    @Override
    public String toString() {
        return "DictionaryDetail{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", name='" + name + '\'' +
                ", value=" + value +
                ", creatTime=" + creatTime +
                '}';
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
