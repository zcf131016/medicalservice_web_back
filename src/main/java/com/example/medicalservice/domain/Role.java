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
    String description;
    String perms;

    public Integer getId() {
        return id;
    }

    public Integer getRoleId() {
        return roleId;
    }



    public String getPerms() {
        return perms;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }
}
