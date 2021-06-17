package com.example.medicalservice.service.impl;

import com.example.medicalservice.domain.User;
import com.example.medicalservice.mapper.UserMapper;
import com.example.medicalservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

}
