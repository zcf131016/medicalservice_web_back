package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.fasterxml.jackson.databind.deser.impl.PropertyValueBuffer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * @USER: zcf
 * @DATE: 2021/6/19 14:30
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    HttpServletRequest request;


    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查找所有用户")
    @ResponseBody
    @GetMapping("/findAllUser")
    public Result getAllUser() {
        List<User> users = userService.findAllUser();
        int Count = users.size();
        return Result.success().setData(users).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有用户成功");
    }


    @ApiOperation(value = "删除用户")
    @ApiImplicitParam(name = "userId",value = "用户Id", paramType = "path",required = true)
    @ResponseBody
    @DeleteMapping (value="/deleteUserByUserid/{userId}")
    public Result deleteUserByUserid(@PathVariable("userId") Integer userId){
        userService.deleteUserByUserId(userId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("根据用户Id删除用户成功");

  }


    @ApiOperation(value = "更新用户")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "userId",value = "用户Id", paramType = "path",required = true)
    )
    @ResponseBody
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        try {
            userService.updateUser(user);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("用户名不能为空！");

        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改用户信息成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询用户根据userid")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name="userId", value="用户id"),
    })
    @ResponseBody
    @GetMapping("/getUserByUserId/{userId}")
    public Result getUserByUserId(@PathVariable("userId")Integer userId) {
        try {
            userService.getUserByUserId(userId);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("用户不存在！");
        }
        User user = userService.getUserByUserId(userId);
        return Result.success().setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }


    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询用户根据username")
    @ApiImplicitParam(required = true,name ="username",value = "username")
    @ResponseBody
    @GetMapping("/getUserByUserName/{username}")
    public Result getUserByUserName(@PathVariable("username")String username) {
        try {
            userService.getUser(username);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("用户不存在！");
        }
        User user = userService.getUser(username);
        return Result.success().setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "分页查找所有用户")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name = "currentPage",value = "当前页数"),
            @ApiImplicitParam(required = true,name = "pageSize",value = "每页显示条数")
    })
    @ResponseBody
    @GetMapping("/selectAllUser")
    public Result selectAllUser(@RequestBody Page page) {
        //数据绑定：包括分页信息，条件，
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<User> pageInfo= userService.selectAllUser(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //想页面返回信息
        //return Result.success().setData(json.toJSONString()).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有用户成功");
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有用户成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询用户根据realname")
    @ApiImplicitParam(required = true,name ="realName",value = "真实姓名")
    @ResponseBody
    @GetMapping("/getUserByRealName")
    public Result getUserByRealName(@RequestBody User user){

       String realName = user.getRealName();
        try {
            userService.getUserByRealName(realName);
        }catch (UserFriendException e){
            return Result.failure(ResultCodeEnum.BAD_REQUEST).setMsg("用户不存在！");
        }
        User user1 = userService.getUserByRealName(realName);
        return Result.success().setData(user1).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询所有教师")
    @ResponseBody
    @GetMapping("/findAllTeacher")
    public Result findAllTeacher() {
        List<User> users = userService.findAllTeacher();
        int Count = users.size();
        return Result.success().setData(users).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有教师成功！");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询所有学生")
    @ResponseBody
    @GetMapping("/findAllStudent")
    public Result findAllStudent() {
        List<User> users = userService.findAllStudent();
        int Count = users.size();
        return Result.success().setData(users).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有学生成功！");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "分页查找所有教师")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name = "currentPage",value = "当前页数"),
            @ApiImplicitParam(required = true,name = "pageSize",value = "每页显示条数")
    })
    @ResponseBody
    @GetMapping("/selectAllTeacher")
    public Result selectAllTeacher(@RequestBody Page page) {
        //数据绑定：包括分页信息，条件，
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<User> pageInfo= userService.selectAllTeacher(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //想页面返回信息
        //return Result.success().setData(json.toJSONString()).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有用户成功");
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有用户成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "分页查找所有学生")
    @ApiImplicitParams({@ApiImplicitParam(required = true,name = "currentPage",value = "当前页数"),
            @ApiImplicitParam(required = true,name = "pageSize",value = "每页显示条数")
    })
    @ResponseBody
    @GetMapping("/selectAllStudent")
    public Result selectAllStudent(@RequestBody Page page) {
        //数据绑定：包括分页信息，条件，
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<User> pageInfo= userService.selectAllStudent(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //想页面返回信息
        //return Result.success().setData(json.toJSONString()).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有用户成功");
        return Result.success().setData(json).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询所有用户成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询用户根据userid数组")
    @ApiImplicitParam(required = true,name ="userIds",value = "userId数组")
    @ResponseBody
    @GetMapping("/getUserByUserIdArray")
    public Result getUserByUserIdArray(@RequestBody User user) {


        List<User> users = userService.getUserByUserIdArray(user.getUserIds());
        int Count = users.size();
        return Result.success().setData(users).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询学生成功！");
    }














}

