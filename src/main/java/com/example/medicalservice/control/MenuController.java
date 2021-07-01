package com.example.medicalservice.control;

import com.example.medicalservice.domain.Menu;
import com.example.medicalservice.domain.MenusDto;
import com.example.medicalservice.service.IMenuService;
import com.example.medicalservice.util.Result;
import com.example.medicalservice.util.ResultCodeEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/26 10:21
 */
@Api(tags = "菜单接口")
@RequiresAuthentication
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    IMenuService menuService;

    @ApiOperation(value="获取所有菜单")
    @GetMapping("/getAllMenu")
    public Result getAllMenu() {
        List<Menu> menuList = menuService.getAllMenu();
        return Result.success().setData(menuList).setCode(ResultCodeEnum.OK.getCode()).setMsg("查询成功");
    }

    @ApiOperation(value = "根据用户 id 获取菜单")
    @GetMapping("/getByUserId/{userId}")
    public Result getMenusByUserId(@PathVariable Integer userId) {
        return Result.success().setData(menuService.getAllMenuByUserId(userId));
    }

    @ApiOperation(value = "根据角色 id 获取菜单")
    @GetMapping("/getByRoleId/{roleId}")
    public Result getAllByRoleId(@PathVariable Integer roleId) {
        return Result.success().setData(menuService.getAllByRoleId(roleId));
    }

    @RequiresRoles("admin")
    @ApiOperation(value = "删除菜单")
    @DeleteMapping("/deleteMenu/{menuId}")
    public Result<Object> remove(@PathVariable Integer menuId) {
        menuService.delete(menuId);
        return Result.success().setMsg("删除成功");
    }

    @RequiresRoles("admin")
    @ApiOperation(value = "添加菜单成功")
    @PostMapping("/addMenu")
    public Result<Object> add(@RequestBody Menu menu) {
        menuService.insert(menu);
        return Result.success().setCode(ResultCodeEnum.CREATED.getCode()).setMsg("添加成功");
    }

    @RequiresRoles("admin")
    @ApiOperation(value="更新菜单", notes = "需要菜单id")
    @PutMapping("/updateMenu")
    public Result updateMenu(@RequestBody Menu menu) {
        menuService.update(menu);
        return Result.success().setCode(ResultCodeEnum.UPDATED.getCode()).setMsg("更新菜单成功");
    }


    @RequiresRoles("admin")
    @ApiOperation(value="批量删除菜单")
    @DeleteMapping("/deleteBatchMenus")
    public Result deleteBatchMenus(@RequestBody MenusDto menus) {
        for(Integer menu : menus.getMenuIds()) {
            menuService.delete(menu);
        }
        return Result.success().setCode(ResultCodeEnum.DELETED.getCode()).setMsg("删除成功!");
    }
}
