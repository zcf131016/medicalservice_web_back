package com.example.medicalservice.domain;


import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author Lin
 * @since 2021-06-19
 */

@ApiModel(value="CommentReply对象", description="")
public class CommentReply implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private Integer likes;

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    @ApiModelProperty(value = "所属案例id")
    private Integer caseId;

    @ApiModelProperty(value = "评论内容")
    private String content;

    @ApiModelProperty(value = "评论者id")
    private Integer fromId;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime creatTime;

    @ApiModelProperty(value = "评论者名称")
    private String fromName;

    @ApiModelProperty(value = "评论者头像")
    private String fromAvatar;

    @ApiModelProperty(value = "是否有回复内容")
    private Integer haveReply;

    @ApiModelProperty(value = "父评论id")
    private Integer parentId;

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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }
    public LocalDateTime getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(LocalDateTime creatTime) {
        this.creatTime = creatTime;
    }
    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }
    public String getFromAvatar() {
        return fromAvatar;
    }

    public void setFromAvatar(String fromAvatar) {
        this.fromAvatar = fromAvatar;
    }
    public Integer getHaveReply() {
        return haveReply;
    }

    public void setHaveReply(Integer haveReply) {
        this.haveReply = haveReply;
    }
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "CommentReply{" +
            "id=" + id +
            ", caseId=" + caseId +
            ", content=" + content +
            ", fromId=" + fromId +
            ", creatTime=" + creatTime +
            ", fromName=" + fromName +
            ", fromAvatar=" + fromAvatar +
            ", haveReply=" + haveReply +
            ", parentId=" + parentId +
        "}";
    }
}
