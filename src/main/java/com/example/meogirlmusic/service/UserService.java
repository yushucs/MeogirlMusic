package com.example.meogirlmusic.service;

import com.example.meogirlmusic.entity.User;

public interface UserService {
    // 根据用户名查询用户
    User findByEmail(String email);
    // 注册
    boolean register(String email, String username, String password);
}
