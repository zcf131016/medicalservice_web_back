package com.example.medicalservice.control;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Api(tags = "用户管理")
@RestController
@RequestMapping("/user")
public class UserControl {
    @Autowired
    UserService userService;
    @ApiOperation(value = "查找所有用户")
    @ResponseBody
    @GetMapping("/findAllUser")
    public Result getAllUser() {
        List<User> users = userService.findAllUser();
        int Count = users.size();
        return Result.success().setData(users).setCode(ResultCodeEnum.OK.getCode()).setCount(Count).setMsg("查询所有用户成功");
    }
}
