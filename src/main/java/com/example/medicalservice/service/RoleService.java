package com.example.medicalservice.service;

import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.Role;
import com.github.pagehelper.PageInfo;
import org.springframework.data.relational.core.sql.In;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/17 8:52
 */
public interface RoleService {
    Role getRole(Integer roleId);
    void addRole(Role role);
    PageInfo getAllRole(Page page);
    void deleteRole(Integer roleId);
}
