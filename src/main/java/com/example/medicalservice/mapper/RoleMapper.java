package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.Role;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author Lin YuHang
 * @date 2021/6/17 8:50
 */
@Mapper
public interface RoleMapper {

    //查询角色
    Role getRole(Integer roleId);
    void addRole(Role role);
    List<Role> getAllRole();
    void deleteRole(Integer roleId);
}
