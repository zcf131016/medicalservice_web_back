package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @DESCRIPTION:
 * @USER: zcf
 * @DATE: 2021/6/19 14:30
 */

@Mapper
public interface UserMapper {

    //查找所有用户
    List<User> findAllUser();

    //查询用户根据username
    User getUser(String username);

    //添加用户
    int insertUser(User user);

    //删除用户
    void deleteUserByUserId(Integer userId);

    //更新用户
    int updateUser(User user);

    //查询用户根据userid
    User getUserByUserId(Integer userId);






}
