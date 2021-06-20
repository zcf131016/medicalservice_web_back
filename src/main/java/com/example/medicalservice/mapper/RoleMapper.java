package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.Role;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author Lin YuHang
 * @date 2021/6/17 8:50
 */
@Mapper
public interface RoleMapper {

    //查询角色
    Role getRole(Integer roleId);

    String getDesc(Integer roleId);


}
