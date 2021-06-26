package com.example.medicalservice.control;

import com.example.medicalservice.domain.Menu;
import com.example.medicalservice.service.IMenuService;
import com.example.medicalservice.util.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/26 10:21
 */
@Api(tags = "菜单接口")
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService menuService;

    @ApiOperation(value = "根据用户 id 获取菜单")
    @GetMapping("/user/{userId}")
    public Result getMenusByUserId(@PathVariable Integer userId) {
        return Result.success().setData(menuService.getAllMenuByUserId(userId));
    }

    @ApiOperation(value = "根据角色 id 获取菜单")
    @GetMapping("/role/{roleId}")
    public Result getAllByRoleId(@PathVariable Integer roleId) {
        return Result.success().setData(menuService.getAllByRoleId(roleId));
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/{menuId}")
    public Result<Object> remove(@PathVariable Integer menuId) {
        menuService.delete(menuId);
        return Result.success().setMsg("删除成功");
    }

    @ApiOperation(value = "添加菜单成功")
    @PostMapping
    public Result<Object> add(@RequestBody Menu menu) {
        menuService.insert(menu);
        return Result.success("添加成功");
    }
}
