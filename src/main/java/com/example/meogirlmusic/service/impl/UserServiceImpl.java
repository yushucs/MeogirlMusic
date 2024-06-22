package com.example.meogirlmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.meogirlmusic.common.utils.Md5Util;
import com.example.meogirlmusic.common.utils.ThreadLocalUtil;
import com.example.meogirlmusic.entity.User;
import com.example.meogirlmusic.mapper.UserMapper;
import com.example.meogirlmusic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private final UserMapper userMapper;


    @Override
    public User findByEmail(String email) {
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email);
        return userMapper.selectOne(queryWrapper);
    }


    @Override
    public boolean updateAvatar(String avatarUrl) {
        // 获取当前线程的用户信息，假设使用 ThreadLocalUtil 存储
        Map<String, Object> map = ThreadLocalUtil.get();
        Long id = (Long) map.get("id");

        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<User>()
                .eq(User::getId, id)
                .set(User::getAvatar, avatarUrl)
                .set(User::getUpdateTime, LocalDateTime.now());

        return userMapper.update(updateWrapper) > 0;
    }


    @Override
    public boolean updatePwd(User user, String newPwd) {
        user.setPassword(Md5Util.getMD5String(newPwd));
        return userMapper.updateById(user) > 0;
    }

    @Override
    public boolean register(User user) {
        // 查询邮箱是否被注册
        User res = findByEmail(user.getEmail());
        if (res != null) {
            return false;
        }

        // 注册
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        save(user);
        return true;
    }
}
