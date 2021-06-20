package com.example.medicalservice.control;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.security.jwt.JWTUtil;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin YuHang//zcf
 * @date 2021/6/17 15:02
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public Result login(@RequestParam("username") String username,
                        @RequestParam("password") String password) {
        System.out.println(username+password);
        User user = userService.getUser(username);
        if (user.getPassWord().equals(password)) {
            System.out.println("登录成功");
            String token = JWTUtil.sign(username, password);
            return Result.success().setToken(token).setCode(ResultCodeEnum.OK.getCode()).setMsg("登录成功！");
        } else {
            return Result.success().setCode(ResultCodeEnum.LoginError.getCode()).setMsg("登录失败！");
        }
    }

    @ApiOperation(value = "注册用户")
    @ResponseBody
    @PostMapping("/register")
    public Result register(@RequestBody User user) {
//        if(userService.getUser(user.getUserName())!=null){
//            return Result.success().setCode(ResultCodeEnum. RegisterAlreadyExist.getCode()).setMsg("注册用户已存在");
//        }
       // System.out.println(user.toString());
        try{
            userService.insertUser(user);
        }catch (UserFriendException e){
            return Result.success().setCode(ResultCodeEnum. RegisterAlreadyExist.getCode()).setMsg("注册用户已存在");
        }

        return Result.success().setCode(ResultCodeEnum. Register.getCode()).setMsg("注册成功");

    }

    @GetMapping("/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public Result unauthorized() {
        return Result.failure(ResultCodeEnum.ILLEGAL_REQUEST);
    }
}
