package com.example.medicalservice.domain;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/28 9:16
 */
public class RoleMenuDto {
    Integer roleId;
    List<Integer> menuIds;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIds() {
        return menuIds;
    }

    public void setMenuIds(List<Integer> menuIds) {
        this.menuIds = menuIds;
    }
}
