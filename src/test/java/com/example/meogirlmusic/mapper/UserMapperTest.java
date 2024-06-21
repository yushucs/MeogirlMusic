package com.example.meogirlmusic.mapper;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.meogirlmusic.common.utils.Md5Util;
import com.example.meogirlmusic.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserMapperTest {
    @Autowired
    private UserMapper userMapper;


    @Test
    public void insert() {
        User user = new User();
        user.setUsername("Test");
        user.setPassword(Md5Util.getMD5String("123456"));
        user.setEmail("test@example.com");
        userMapper.insert(user);
    }

    @Test
    public void findByEmail() {
        String email = "test@example.com";
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<User>()
                .eq(User::getEmail, email);
        System.out.println(userMapper.selectOne(queryWrapper));
    }
}
