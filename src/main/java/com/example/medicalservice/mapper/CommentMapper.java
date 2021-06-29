package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.CommentReply;
import com.example.medicalservice.domain.Like;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<CommentReply> getComments(Integer caseId);
    CommentReply getCommentById(Integer id);
    void insertComment(CommentReply commentReply);
    void deleteComment(Integer id);
    void updateComment(CommentReply commentReply);
    Integer getLikes(Integer commentId);
    void insertLikes(Integer commentId, Integer userId);
    void updateLikes(Integer id);
    Like getLike(Integer commentId, Integer userId);
}
