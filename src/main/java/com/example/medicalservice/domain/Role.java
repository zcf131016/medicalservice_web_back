package com.example.medicalservice.domain;

import io.swagger.annotations.ApiModel;

/**
 * @author Lin YuHang
 * @date 2021/6/17 8:45
 */

@ApiModel
public class Role {
    Integer id;
    Integer roleId;
    String Desc;
    String Perms;

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleId=" + roleId +
                ", Desc='" + Desc + '\'' +
                ", Perms='" + Perms + '\'' +
                '}';
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public void setDesc(String desc) {
        Desc = desc;
    }

    public void setPerms(String perms) {
        Perms = perms;
    }

    public Integer getId() {
        return id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public String getDesc() {
        return Desc;
    }

    public String getPerms() {
        return Perms;
    }
}
