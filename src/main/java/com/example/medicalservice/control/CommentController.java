package com.example.medicalservice.control;

import com.example.medicalservice.domain.Comment;
import com.example.medicalservice.domain.CommentReply;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.service.CommentReplyService;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/19 16:14
 */
@Api(tags = "评论接口")
@RestController
@RequiresRoles(value = {"admin","teacher","student"}, logical = Logical.OR)
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentReplyService commentReplyService;

    @Autowired
    UserService userService;


    @ApiOperation(value = "根据案例id获取评论")
    @ApiParam(name="caseId",type = "Integer")
    @GetMapping("/getComments/{caseId}")
    public Result getComments(@PathVariable Integer caseId) {
        List<Comment> comments = commentReplyService.getComments(caseId);
        return Result.success().setData(comments).setCode(ResultCodeEnum.OK.getCode()).setMsg("评论获取成功!");
    }

    @ApiOperation(value = "添加评论",notes = "不需要传userid")
    @PostMapping("/addComment")
    public Result addComment(@RequestBody CommentReply commentReply) {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.getUser(subject.getPrincipal().toString());
        commentReply.setFromId(user.getUserId());
        if(commentReply.getContent() == null || commentReply.getContent() == "") return Result.failure(ResultCodeEnum.CREATE_FAILED).setMsg("内容为空，评论失败");
        commentReplyService.insertComment(commentReply);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("评论成功！");
    }

    /*
        只有自己能删除自己的评论, 管理员可删除所有
     */
    @ApiOperation(value="根据评论id删除评论",notes = "只有自己能删除自己的评论")
    @DeleteMapping("/deleteComment/{id}")
    public Result deleteComment(@PathVariable Integer id) {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.getUser(subject.getPrincipal().toString());
        try {
            CommentReply commentReply = commentReplyService.getComment(id);
            if(commentReply != null && (user.getId() == commentReply.getFromId() || subject.hasRole("admin"))) {
                commentReplyService.deleteComment(id);
            } else {
                throw new RuntimeException("删除失败");
            }
        } catch (RuntimeException e) {
            return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST).setMsg("无法删除他人评论！");
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("删除评论成功！");
    }

    @ApiOperation(value="修改评论", notes = "只有自己能修改评论")
    @PutMapping("/modifyComment")
    public Result modifyComment(@RequestBody CommentReply commentReply) {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.getUser(subject.getPrincipal().toString());
        if(user.getId() == commentReply.getFromId() || subject.hasRole("admin")) {
            commentReplyService.updateComment(commentReply);
            return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("评论修改成功!");
        }
        return Result.failure(ResultCodeEnum.UNAUTHORIZED);
    }

    @ApiOperation(value="点赞接口", notes = "只需要评论id,用户id将根据token获取")
    @PostMapping("/likes/{commentId}")
    public Result likes(@PathVariable Integer commentId) {
        Subject subject = SecurityUtils.getSubject();
        User user = userService.getUser(subject.getPrincipal().toString());
        commentReplyService.likes(commentId, user.getUserId());
        return Result.success().setMsg("点赞成功！");
    }
}
