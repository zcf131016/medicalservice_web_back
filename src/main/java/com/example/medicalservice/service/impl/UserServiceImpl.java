package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.exception.UserFriendException;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.service.UserService;
import com.example.medicalservice.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public User getUser(String username) {return userMapper.getUser(username);}

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
        return userMapper.updateUser(user);
    }

    @Override
    public User getUserByUserId(Integer userId) {
        return userMapper.getUserByUserId(userId);
    }

}
