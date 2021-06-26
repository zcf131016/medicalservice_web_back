package com.example.medicalservice.service;

import com.example.medicalservice.domain.Menu;

import java.util.List;


/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Lin
 * @since 2021-06-26
 */
public interface IMenuService {

    void insert(Menu menu);

    void delete(Integer menuId);

    public int update(Menu menu);

    public List<Menu> getAllMenuByUserId(Integer userId);

    public List<Menu> getAllByRoleId(Integer roleId);


}
