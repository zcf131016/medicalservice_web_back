package com.example.medicalservice.control;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


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

    //@RequiresRoles("teacher")
    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查找所有用户")
    @ResponseBody
    @GetMapping("/findAllUser")
    public Result getAllUser() {
        PageHelper.startPage(1,2);
        List<User> users = userService.findAllUser();
        int Count = users.size();
        return Result.success().setData(users).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有用户成功");
    }


//    @ApiOperation(value = "按用户名查找用户")
//    @ResponseBody
//    @GetMapping("/getUser/{username}")
//    public Result getUser(@PathVariable("username") String username) {
//        User user = userService.getUser(username);
//        Subject subject = SecurityUtils.getSubject();
////        if(subject.hasRole("admin")) {
////            return Result.success().setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
////        }
//        try {
//            subject.checkRole("admin");
//        } catch (AuthorizationException e) {
//            return Result.failure(ResultCodeEnum.FORBIDDEN).setMsg(e.getMessage());
//        }
//        return Result.success().setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
//    }




    @ApiOperation(value = "删除用户")
    @ResponseBody
    @RequestMapping(value="/deleteUserByUserid/{userId}", method=RequestMethod.POST)
    public Result deleteUserByUserid(@PathVariable("userId") Integer userId){
        userService.deleteUserByUserId(userId);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("根据用户Id删除用户成功");

  }


    @ApiOperation(value = "更新用户")
    @ResponseBody
    @PostMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("修改用户信息成功");

    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询用户根据userid")
    @ResponseBody
    @GetMapping("/getUserByUserId/{userId}")
    public Result getUserByUserId(@PathVariable("userId")Integer userId) {
        User user = userService.getUserByUserId(userId);
        return Result.success().setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @RequiresRoles(value={"teacher","admin"},logical=Logical.OR)
    @ApiOperation(value = "查询用户根据username")
    @ResponseBody
    @GetMapping("/getUserByUserName/{username}")
    public Result getUserByUserName(@PathVariable("username")String username) {
        User user = userService.getUser(username);
        return Result.success().setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }


}
