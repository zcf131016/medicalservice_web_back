package com.example.medicalservice.control;

import com.alibaba.fastjson.JSONObject;
import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.Role;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.service.RoleService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresRoles;
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

    @RequiresRoles("admin")
    @ApiOperation(value="根据角色id获取角色")
    @RequiresAuthentication
    @ResponseBody
    @GetMapping("/getRole/{roleId}")
    public Result getRole(@PathVariable Integer roleId) {
        return Result.success().setData(roleService.getRole(roleId)).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询角色成功");
    }

    @ApiOperation(value="添加角色")
    @RequiresRoles("admin")
    @PostMapping("/addRole")
    public Result addRole(@RequestBody Role role) {
        System.out.println(role.toString());
        try {
            roleService.addRole(role);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.CREATE_FAILED);
        }
        return Result.success().setCode(ResultCodeEnum.OK.getCode()).setMsg("添加角色成功");
    }

    @ApiOperation(value="获取所有角色")
    @RequiresRoles("admin")
    @GetMapping("/getAllRole/{pageNum}/{pageSize}")
    public Result getAllRole(@PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        Page page = new Page();
        page.setCurrentPage(pageNum);
        page.setPageSize(pageSize);
        JSONObject json = new JSONObject();
        try {
            //调用查询所有信息方法，并将从页面接受的页面和每页显示的信息数传过去
            PageInfo<Role> pageInfo= roleService.getAllRole(page);
            //将查出的信息封装为json
            json.put("pageInfo", pageInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.failure(ResultCodeEnum.INQUIRE_FAILED);
        }
        return Result.success().setData(json.get("pageInfo")).setCode(ResultCodeEnum.OK.getCode()).setMsg("角色查询成功！");
    }

    @ApiOperation(value="根据角色id删除角色")
    @RequiresRoles("admin")
    @DeleteMapping("/deleteRole/{roleId}")
    public Result deleteRole(@PathVariable("roleId") Integer roleId) {
        try {
            roleService.deleteRole(roleId);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.DELETE_FAILED);
        }
        return Result.success().setCode(ResultCodeEnum.DELETED.getCode());
    }

    @ApiOperation(value="根据id更新角色",notes = "不是roleId")
    @RequiresRoles("admin")
    @PutMapping("/updateRole")
    public Result updateRole(@RequestBody Role role) {
        try {
            roleService.updateRole(role);
        } catch (UserFriendException e) {
            return Result.failure(ResultCodeEnum.UPDATE_FAILED).setMsg("更新失败,请检查参数");
        }
        return Result.success().setCode(ResultCodeEnum.UPDATED.getCode()).setMsg("更新成功");
    }
}
