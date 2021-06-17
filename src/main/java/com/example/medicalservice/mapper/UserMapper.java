package com.example.medicalservice.mapper;

import com.example.medicalservice.domain.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface UserMapper {
    List<User> findAllUser();
    User getUser(String username);
}
