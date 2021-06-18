package com.example.medicalservice.service;

import com.example.medicalservice.domain.User;

import java.util.List;

public interface UserService {
    public List<User> findAllUser();
    User getUser(String username);
}
