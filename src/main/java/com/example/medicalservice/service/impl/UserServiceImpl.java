package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.DictionaryType;
import com.example.medicalservice.domain.Page;
import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.RandomUtil;
import com.example.medicalservice.util.ResultCodeEnum;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @DESCRIPTION:
 * @USER: zcf
 * @DATE: 2021/6/19 14:30
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAllUser() {
        return userMapper.findAllUser();
    }

    @Override
    public User getUser(String username) {
        if(userMapper.getUser(username)==null){
            throw new UserFriendException("用户不存在!","103");
        }
        return userMapper.getUser(username);}

    @Override
    public int insertUser(User user) {

        if(userMapper.getUser(user.getUserName())!=null){
            throw new UserFriendException("用户名已存在!","103");
        }

        user.setUserId(RandomUtil.getRandom(9));//生成10位的userid
        //System.out.println(user);
        if(user.getRoleId()==null) {
            user.setRoleId(2);//默认角色为学生
        }
        //System.out.println(user);
        return userMapper.insertUser(user);
    }

    @Override
    public void deleteUserByUserId(Integer userId) {
        userMapper.deleteUserByUserId(userId);
    }

    @Override
    public int updateUser(User user) {
        if(user.getUserName()==null){
            throw new UserFriendException("用户名不能为空",ResultCodeEnum.PARAMS_MISS.getCode());
        }
        return userMapper.updateUser(user);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        if(userMapper.getUserByUserId(userId)==null){
            throw new UserFriendException("用户不存在!","103");
        }
        return userMapper.getUserByUserId(userId);
    }

    @Override
    public PageInfo  selectAllUser(Page page) {
        //设置页码和每页个数
        //System.out.println(page.getCurrentPage());
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        //查询符合条件的记录
        List<User> users = userMapper.selectAllUser();
        if(users.size()==0) throw new UserFriendException();
        //将查询到的信息封装到PageInfo
        PageInfo pageInfo = new PageInfo(users);
        //返回PageInfo类型数据
        return pageInfo;

    }

    @Override
    public User getUserByRealName(String realName) {
        if(userMapper.getUserByRealName(realName)==null){
            throw new UserFriendException("用户不存在!","103");
        }
        return userMapper.getUserByRealName(realName);
    }

    @Override
    public List<User> findAllTeacher() {
        return userMapper.findAllTeacher();
    }

    @Override
    public List<User> findAllStudent() {
        return userMapper.findAllStudent();
    }

    @Override
    public PageInfo selectAllTeacher(Page page) {
        //设置页码和每页个数
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        //查询符合条件的记录
        List<User> users = userMapper.selectAllTeacher();
        if(users.size()==0) throw new UserFriendException();
        //System.out.println(users);
        //将查询到的信息封装到PageInfo
        PageInfo pageInfo = new PageInfo(users);
        //返回PageInfo类型数据
        return pageInfo;
    }



    @Override
    public PageInfo selectAllStudent(Page page) {
        //设置页码和每页个数
        PageHelper.startPage(page.getCurrentPage(),page.getPageSize());
        //查询符合条件的记录
        List<User> users = userMapper.selectAllStudent();
        //将查询到的信息封装到PageInfo
        PageInfo pageInfo = new PageInfo(users);
        if(users.size()==0) throw new UserFriendException();
        //返回PageInfo类型数据
        return pageInfo;

    }

    @Override
    public List<User> getUserByUserIdArray(Integer[] userIds) {
        List<User> users = new ArrayList<>();
        for(int i=0;i<userIds.length;i++){
            User user = userMapper.getUserByUserId(userIds[i]);
            users.add(user);
        }
        return users;
    }

    @Override
    public int deleteUserAllById(List<Integer> userIds) {
        return userMapper.deleteUserAllByUserIds(userIds);
    }

    @Override
    public User getUserByEmail(String email) {

//        if (userMapper.getUserByEmail(email) == null) {
//            throw new UserFriendException("用户不存在!", "103");
//        }


        return userMapper.getUserByEmail(email);
    }

    @Override
    public User getUserByPhone(String phone) {

//        if(userMapper.getUserByPhone(phone)==null){
//            throw new UserFriendException("用户不存在!","103");
//        }

        return userMapper.getUserByPhone(phone);
    }

    @Override
    public int updatePasswordByEmail(String email, String passWord) {
        if (userMapper.getUserByEmail(email)==null){
            throw new UserFriendException("邮箱输入错误,用户不存在","103");
        }
        return userMapper.updatePasswordByEmail(email,passWord);
    }

    @Override
    public List<User> findAllTeacherNotInCourse(Integer courseId) {
        return userMapper.findAllTeacherNotInCourse(courseId);
    }

    @Override
    public List<User> findAllStudentNotInCourse(Integer courseId) {
        return userMapper.findAllStudentNotInCourse(courseId);
    }

}
