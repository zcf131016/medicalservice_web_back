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
    Result getComments(@PathVariable Integer caseId) {
        List<Comment> comments = commentReplyService.getComments(caseId);
        return Result.success().setData(comments).setCode(ResultCodeEnum.OK.getCode()).setMsg("评论获取成功!");
    }

    @ApiOperation(value = "添加评论")
    @PostMapping("/addComment")
    Result addComment(@RequestBody CommentReply commentReply) {
        commentReplyService.insertComment(commentReply);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("评论成功！");
    }

    /*
        只有自己能删除自己的评论, 管理员可删除所有
     */
    @ApiOperation(value="根据评论id删除评论",notes = "只有自己能删除自己的评论")
    @DeleteMapping("/deleteComment/{id}")
    Result deleteComment(@PathVariable Integer id) {
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
}
