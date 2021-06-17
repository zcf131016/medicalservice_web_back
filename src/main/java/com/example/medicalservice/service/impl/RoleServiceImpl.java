package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.Role;
import com.example.medicalservice.mapper.RoleMapper;
import com.example.medicalservice.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Lin YuHang
 * @date 2021/6/17 8:53
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleMapper roleMapper;

    @Override
    public Role getRole(Integer roleId) {
        return roleMapper.getRole(roleId);
    }

    @Override
    public String getDesc(Integer roleId) {
        return roleMapper.getDesc(roleId);
    }
}
