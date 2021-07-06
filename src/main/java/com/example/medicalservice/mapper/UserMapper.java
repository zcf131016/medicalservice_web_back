package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

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

    //分页查询
    List<User> selectAllUser();

    //查询用户根据realname
    User getUserByRealName(String realName);

    //查询所有老师
    List<User> findAllTeacher();

    //查询所有学生
    List<User> findAllStudent();

    //分页查询所有老师
    List<User> selectAllStudent();

    //分页查询所有学生
    List<User> selectAllTeacher();

    //根据userId批量删除
    int deleteUserAllByUserIds(List<Integer> userIds);

    //查询用户根据email
    User getUserByEmail(String email);

    //查询用户根据phone
    User getUserByPhone(String phone);

    //更新密码
    int updatePasswordByEmail(String email,String passWord);

    //查询不在这门课的老师信息
    List<User> findAllTeacherNotInCourse(Integer courseId);







}









