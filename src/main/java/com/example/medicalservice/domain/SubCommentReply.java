package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;

@ApiModel
public class SubCommentReply {
    private  Integer id;
    private Integer parentId;
    private Integer childId;
    private Date creatTime;

    @Override
    public String toString() {
        return "SubCommentReply{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", childId=" + childId +
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

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public Integer getChildId() {
        return childId;
    }

    public void setChildId(Integer childId) {
        this.childId = childId;
    }
}
