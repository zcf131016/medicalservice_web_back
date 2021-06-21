package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
import java.util.List;
@ApiModel(value = "字典类型实体")
public class DictionaryType {

    private Integer id;
    private String typeCode;
    private String typeName;
    private Date creatTime;
    private List<DictionaryDetail> dictionaryDetails;

    @Override
    public String toString() {
        return "DictionaryType{" +
                "id=" + id +
                ", typeCode='" + typeCode + '\'' +
                ", typeName='" + typeName + '\'' +
                ", creatTime=" + creatTime +
                ", dictionaryDetails=" + dictionaryDetails +
                '}';
    }

    public List<DictionaryDetail> getDictionaryDetails() {
        return dictionaryDetails;
    }

    public void setDictionaryDetails(List<DictionaryDetail> dictionaryDetails) {
        this.dictionaryDetails = dictionaryDetails;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
