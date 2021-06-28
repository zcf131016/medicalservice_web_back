package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.RoleMenuDto;
import com.example.medicalservice.mapper.RoleMenuMapper;
import com.example.medicalservice.service.RoleMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lin YuHang
 * @date 2021/6/28 9:40
 */
@Service
public class RoleMenuServiceImpl implements RoleMenuService {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    @Override
    public void insertRoleMenu(RoleMenuDto roleMenuDto) {
        roleMenuMapper.insertRoleMenu(roleMenuDto);
    }

    @Override
    public void deleteRoleMenuByRoleId(Integer roleId) {
        roleMenuMapper.deleteRoleMenuByRoleId(roleId);
    }
}
