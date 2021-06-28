package com.example.medicalservice.domain;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/28 9:12
 */
public class RoleMenu {
    Integer id;
    Integer roleId;
    Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
