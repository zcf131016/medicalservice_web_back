package com.example.medicalservice.control;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.security.jwt.JWTUtil;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin YuHang//zcf
 * @date 2021/6/17 15:02
 */
@Api(tags = "登录注册相关")
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @ApiOperation(value="登录接口")
    @ResponseBody
    @PostMapping("/login")
    public Result login(@RequestBody User users) {
        String username = users.getUserName();
        String password = users.getPassWord();
        User user = userService.getUser(username);
        if (user != null && user.getPassWord().equals(password)) {
            System.out.println("登录成功");
            String token = JWTUtil.sign(username, password);
            user.setPassWord(null);
            return Result.success().setToken(token).setData(user).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功！");
        } else {
            return Result.success().setCode(ResultCodeEnum.LoginError.getCode()).setMsg("登录失败！");
        }
    }

    @ApiOperation(value = "注册用户")
    @ApiResponses({
            @ApiResponse(code=101,message="注册成功"),
            @ApiResponse(code=103,message="用户已存在")
    })
    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody User user) {

        try{
            userService.insertUser(user);
        }catch (UserFriendException e){
            return Result.success().setCode(ResultCodeEnum.RegisterAlreadyExist.getCode()).setMsg(e.getMsg());
        }
        return Result.success().setCode(ResultCodeEnum.Register.getCode()).setMsg("注册成功");
    }

    @GetMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST);
    }
}
