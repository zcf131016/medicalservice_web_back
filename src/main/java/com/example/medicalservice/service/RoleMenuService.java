package com.example.medicalservice.service;

import com.example.medicalservice.domain.RoleMenuDto;

/**
 * @author Lin YuHang
 * @date 2021/6/28 9:39
 */
public interface RoleMenuService {
    void insertRoleMenu(RoleMenuDto roleMenuDto);
    void deleteRoleMenuByRoleId(Integer roleId);
}
