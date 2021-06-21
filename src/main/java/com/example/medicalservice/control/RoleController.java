package com.example.medicalservice.control;

import com.example.medicalservice.service.RoleService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Lin YuHang
 * @date 2021/6/17 9:12
 */
@Api(tags = "角色接口")
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    RoleService roleService;

    @RequiresAuthentication
    @ResponseBody
    @GetMapping("/getRole/{roleId}")
    public Result getRole(@PathVariable Integer roleId) {
        return Result.success().setData(roleService.getRole(roleId)).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询角色成功");
    }
}
