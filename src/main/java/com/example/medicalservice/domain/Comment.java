package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/19 16:31
 */
@ApiModel(value="Comment对象", description="真实返回的评论类型")
public class Comment {
    Integer id;
    Integer caseId;
    String content;
    Integer fromId;
    LocalDateTime createTime;
    String fromName;
    String fromAvatar;
    Integer haveReply;
    Integer likes;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", caseId=" + caseId +
                ", content='" + content + '\'' +
                ", fromId=" + fromId +
                ", createTime=" + createTime +
                ", fromName='" + fromName + '\'' +
                ", fromAvatar='" + fromAvatar + '\'' +
                ", haveReply=" + haveReply +
                ", parentId=" + parentId +
                ", reply=" + reply +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setCaseId(Integer caseId) {
        this.caseId = caseId;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }

    public void setHaveReply(Integer haveReply) {
        this.haveReply = haveReply;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public void setReply(List<CommentReply> reply) {
        this.reply = reply;
    }

    public Integer getId() {
        return id;
    }

    public Integer getCaseId() {
        return caseId;
    }

    public String getContent() {
        return content;
    }

    public Integer getFromId() {
        return fromId;
    }

    public LocalDateTime getCreateTime() {
        return createTime;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromAvatar() {
        return fromAvatar;
    }

    public Integer getHaveReply() {
        return haveReply;
    }

    public Integer getParentId() {
        return parentId;
    }

    public List<CommentReply> getReply() {
        return reply;
    }

    Integer parentId;
    List<CommentReply> reply;
}
