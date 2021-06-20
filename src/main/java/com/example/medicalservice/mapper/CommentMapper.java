package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.CommentReply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentReply> getComments(Integer caseId);
    CommentReply getCommentById(Integer id);
    void insertComment(CommentReply commentReply);
    void deleteComment(Integer id);
    void updateComment(CommentReply commentReply);
}
