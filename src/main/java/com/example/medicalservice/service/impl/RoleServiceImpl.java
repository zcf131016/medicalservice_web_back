package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.Role;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.mapper.RoleMapper;
import com.example.medicalservice.service.RoleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void addRole(Role role) {
        if(roleMapper.getRole(role.getRoleId()) != null) {
            throw new UserFriendException();
        }
        roleMapper.addRole(role);
    }

    @Override
    public PageInfo getAllRole(Page page) {
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
         List<Role> roles = roleMapper.getAllRole();
         if(roles.size() == 0) throw new UserFriendException();
         PageInfo pageInfo = new PageInfo(roles);
         return pageInfo;
    }

    @Override
    public void deleteRole(Integer roleId) {
        Role role = roleMapper.getRole(roleId);
        if(role == null) throw new UserFriendException();
        roleMapper.deleteRole(roleId);
    }
}
