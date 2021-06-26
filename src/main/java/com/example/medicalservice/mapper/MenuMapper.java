package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Lin
 * @since 2021-06-26
 */

@Mapper
public interface MenuMapper {
    List<Menu> selectAllMenuByUserId(Integer userId);

    List<Menu> selectAllByRoleId(Integer roleId);

    int deleteByPrimaryKey(Integer menuId);

    int insert(Menu menu);

    int insertBatch(List<Menu> menuList);

    int update(Menu menu);
}
