package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.common.utils.Md5Util;
import com.example.meogirlmusic.entity.User;
import com.example.meogirlmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping(value = "/login")
    public String login(String email, String password) {
        User user = userService.findByEmail(email);
        System.out.println(email + password + user);
        if (user == null) {
            return new ErrorMessage("该邮箱未注册").getMessage();
        }
        //判断密码是否正确  loginUser对象中的password是密文
        if (Md5Util.checkPassword(password, user.getPassword())) {
            return new SuccessMessage<>("登录成功").getMessage();
        }
        return new ErrorMessage("密码错误").getMessage();
    }

    @PostMapping(value = "/register")
    public String register(String email, String username, String password) {
        User res = userService.findByEmail(email);
        if (res != null) {
            return new ErrorMessage("该邮箱已被注册").getMessage();
        }

        if (userService.register(email, username, password)) {
            return new SuccessMessage<>("注册成功").getMessage();
        } else {
            return new ErrorMessage("注册失败，请稍后尝试").getMessage();
        }
    }
}
