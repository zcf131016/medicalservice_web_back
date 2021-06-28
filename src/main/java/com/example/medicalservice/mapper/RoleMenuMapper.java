package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.RoleMenuDto;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lin YuHang
 * @date 2021/6/28 9:19
 */
@Mapper
public interface RoleMenuMapper {
    void insertRoleMenu(RoleMenuDto roleMenuDto);
    void deleteRoleMenuByRoleId(Integer roleId);
}
