package com.example.medicalservice.service;

import com.example.medicalservice.domain.Role;
import org.springframework.stereotype.Service;

/**
 * @author Lin YuHang
 * @date 2021/6/17 8:52
 */
public interface RoleService {
    Role getRole(Integer roleId);
    String getDesc(Integer roleId);
}
