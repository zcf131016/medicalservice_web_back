package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.util.Date;
@ApiModel
public class CommentReply {
    private Integer id;
    private Integer caseId;
    private Integer commentId;
    private String content;
    private Integer formId;
    private String formName;
    private Date creatTime;

    @Override
    public String toString() {
        return "CommentReply{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", commentId=" + commentId +
                ", content='" + content + '\'' +
                ", formId=" + formId +
                ", formName='" + formName + '\'' +
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

    public Integer getCaseId() {
        return caseId;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getFormId() {
        return formId;
    }

    public void setFormId(Integer formId) {
        this.formId = formId;
    }

    public String getFormName() {
        return formName;
    }

    public void setFormName(String formName) {
        this.formName = formName;
    }
}
