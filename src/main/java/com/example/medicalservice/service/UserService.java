package com.example.medicalservice.service;

import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @DESCRIPTION:
 * @USER: zcf
 * @DATE: 2021/6/19 14:30
 */

public interface UserService {

    public List<User> findAllUser();

    User getUser(String username);

    //添加用户
    int insertUser(User user);

    //删除用户
    void deleteUserByUserId(Integer userId);

    //更新用户
    int updateUser(User user);

    //查询用户根据userid
    User getUserByUserId(Integer userId);

    //分页查询用户
    PageInfo selectAllUser(Page page);

    //查询用户根据realname
    User getUserByRealName(String realName);

    //查询所有老师
    List<User> findAllTeacher();

    //查询所有学生
    List<User> findAllStudent();

    //分页查询所有老师
    PageInfo selectAllTeacher(Page page);

    //分页查询所有学生
    PageInfo selectAllStudent(Page page);

    //查询用户根据userid数组(批量查询)
    List<User> getUserByUserIdArray(Integer [] userIds);

    //根据useId批量删除用户
    int deleteUserAllById(List<Integer> userIds);

    //查询用户根据email
    User getUserByEmail(String email);

    //查询用户根据phone
    User getUserByPhone(String phone);

    //更新密码
    int updatePasswordByEmail(String email,String passWord);

    //查询不在这门课的老师信息
    List<User> findAllTeacherNotInCourse(Integer courseId);





}
