package com.example.meogirlmusic.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.meogirlmusic.entity.User;

public interface UserService extends IService<User> {
    // 根据用户名查询用户
    User findByEmail(String email);

    boolean updateAvatar(String avatarUrl);

    boolean updatePwd(User user, String newPwd);

    boolean register(User user);
}
