package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.Menu;
import com.example.medicalservice.mapper.MenuMapper;
import com.example.medicalservice.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Lin
 * @since 2021-06-26
 */
@Service
public class MenuServiceImpl  implements IMenuService {

    @Autowired
    MenuMapper menuMapper;

    public void insert(Menu menu) {
        menuMapper.insert(menu);
    }

    public int update(Menu menu) {
        return menuMapper.update(menu);
    }

    public void delete(Integer id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    public List<Menu> getAllMenuByUserId(Integer userId) {
        List<Menu> menuList = menuMapper.selectAllMenuByUserId(userId);
        return buildTree(menuList);
    }

    public List<Menu> getAllByRoleId(Integer roleId) {
        List<Menu> menuList = menuMapper.selectAllByRoleId(roleId);
        return buildTree(menuList);
    }

    @Override
    public List<Menu> getAllMenu() {
        List<Menu> menuList = menuMapper.selectAllMenu();
        return buildTree(menuList);
    }

    private List<Menu> buildTree(List<Menu> rawMenu) {
        List<Menu> menuTree = new LinkedList<>();
        for (Iterator<Menu> iterator = rawMenu.iterator(); iterator.hasNext(); ) {
            Menu curr = iterator.next();
            if (curr.getParentId() == 0) {
                iterator.remove();
                curr.setChildren(getChild(rawMenu, curr));
                menuTree.add(curr);
            }
        }
        rawMenu.clear();
        return menuTree;
    }

    private List<Menu> getChild(List<Menu> menus, Menu parent) {

        List<Menu> child = new LinkedList<>();

        for (Menu menu : menus) {
            if (menu.getParentId().equals(parent.getId())) {
                menu.setChildren(getChild(menus, menu));
                child.add(menu);
            }
        }
        return child;
    }
}
