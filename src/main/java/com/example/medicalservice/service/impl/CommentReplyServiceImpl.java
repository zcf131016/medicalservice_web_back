package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.Comment;
import com.example.medicalservice.domain.CommentReply;
import com.example.medicalservice.domain.Like;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.mapper.CommentMapper;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.service.CommentReplyService;
import com.example.medicalservice.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/19 15:54
 */
@Service
public class CommentReplyServiceImpl implements CommentReplyService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    UserMapper userMapper;


    @Override
    public List<Comment> getComments(Integer caseId) {
        List<Comment> comments = new ArrayList<>();
        List<CommentReply> data = commentMapper.getComments(caseId);
        for(int i = 0;i < data.size(); ++i) {
            Comment comment = new Comment();
            CommentReply curComment = data.get(i);
            if(curComment.getParentId()==null) {
                comment.setId(curComment.getId());
                comment.setCaseId(curComment.getCaseId());
                comment.setContent(curComment.getContent());
                comment.setFromId(curComment.getFromId());
                comment.setFromName(curComment.getFromName());
                comment.setFromAvatar(curComment.getFromAvatar());
                comment.setHaveReply(curComment.getHaveReply());
                comment.setCreateTime(curComment.getCreatTime());
                comment.setLikes(commentMapper.getLikes(curComment.getId()));
                if(comment.getHaveReply() == 1) {
                    List<CommentReply> replies = new ArrayList<>();
                    for(CommentReply c : data) {
                        if(c.getParentId() != null && c.getParentId() == comment.getId()) {
                            c.setLikes(commentMapper.getLikes(c.getId()));
                            replies.add(c);
                        }
                    }
                    comment.setReply(replies);
                }
                comments.add(comment);
            }
        }
        return comments;
    }

    @Override
    public CommentReply getComment(Integer id) {
        return commentMapper.getCommentById(id);
    }

    @Override
    public void insertComment(CommentReply commentReply) {
        if(commentReply.getParentId() != null){
           CommentReply parentComment = getComment(commentReply.getParentId());
           if(parentComment != null) {
               parentComment.setHaveReply(1);
               commentMapper.updateComment(parentComment);
           }
        }
        User user = userMapper.getUserByUserId(commentReply.getFromId());
        commentReply.setFromAvatar(user.getAvatar());
        commentMapper.insertComment(commentReply);
    }

    @Override
    public void deleteComment(Integer id) {
        CommentReply commentReply = commentMapper.getCommentById(id);
        if(commentReply != null && commentReply.getHaveReply() != 1) {
            commentMapper.deleteComment(id);
        } else {
            commentReply.setContent("< 该评论已被作者删除！>");
            commentMapper.updateComment(commentReply);
        }
    }

    @Override
    public void updateComment(CommentReply commentReply) {
        commentMapper.updateComment(commentReply);
    }

    @Override
    public void likes(Integer commentId, Integer userId) {
        Like like = commentMapper.getLike(commentId, userId);
        if(like != null) { // 有点赞记录, 更新
            commentMapper.updateLikes(like.getId());
        } else { // 无点赞记录，添加
            commentMapper.insertLikes(commentId, userId);
        }
    }
}
