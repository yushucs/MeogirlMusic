package com.example.meogirlmusic.mapper;


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
        user.setPassword("123456");
        user.setEmail("test@example.com");
        userMapper.insert(user);
    }
}
