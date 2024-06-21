package com.example.meogirlmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.meogirlmusic.common.utils.Md5Util;
import com.example.meogirlmusic.entity.User;
import com.example.meogirlmusic.mapper.UserMapper;
import com.example.meogirlmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }

    @Override
    public boolean register(String email, String username, String password) {
        User user = User.builder()
                .email(email)
                .username(username)
                .password(Md5Util.getMD5String(password))
                .build();
        // 插入成功返回 true
        return userMapper.insert(user) > 0;
    }
}
