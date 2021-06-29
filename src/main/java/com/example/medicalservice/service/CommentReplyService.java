package com.example.medicalservice.service;

import com.example.medicalservice.domain.Comment;
import com.example.medicalservice.domain.CommentReply;
import com.example.medicalservice.domain.Like;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/19 15:52
 */

public interface CommentReplyService {
    List<Comment> getComments(Integer caseId);
    CommentReply getComment(Integer id);
    void insertComment(CommentReply commentReply);
    void deleteComment(Integer id);
    void updateComment(CommentReply commentReply);
    void likes(Integer commentId, Integer userId);
}
