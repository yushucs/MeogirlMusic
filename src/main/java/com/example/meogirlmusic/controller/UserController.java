package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.common.utils.JwtUtil;
import com.example.meogirlmusic.common.utils.Md5Util;
import com.example.meogirlmusic.common.utils.ThreadLocalUtil;
import com.example.meogirlmusic.entity.User;
import com.example.meogirlmusic.service.UserService;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Validated
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final StringRedisTemplate stringRedisTemplate;

    @Autowired
    public UserController(UserService userService, StringRedisTemplate stringRedisTemplate) {
        this.userService = userService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @PostMapping(value = "/login")
    public String login(@RequestParam @Email String email, @RequestParam @NotBlank String password) {
        User user = userService.findByEmail(email);
        if (user == null) {
            return new ErrorMessage("该邮箱未注册").getMessage();
        }

        //判断密码是否正确  loginUser对象中的password是密文
        if (Md5Util.checkPassword(password, user.getPassword())) {
            // 生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", user.getId());
            claims.put("email", user.getEmail());
            claims.put("username", user.getUsername());
            String token = JwtUtil.genToken(claims);

            // 将token存储到redis数据库
            ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
            ops.set(token, token, 1, TimeUnit.HOURS);

            return new SuccessMessage<>("登录成功", token).getMessage();
        }
        return new ErrorMessage("密码错误").getMessage();
    }

    @PostMapping(value = "/register")
    public String register(@RequestParam @Email String email, @RequestParam @NotBlank String username, @RequestParam @NotBlank String password) {
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

    @PutMapping("/update")
    public String update(@RequestBody User user) {
        if (userService.update(user)) {
            return new SuccessMessage<>("更新成功").getMessage();
        } else {
            return new ErrorMessage("更新失败，请稍后尝试").getMessage();
        }
    }

    @PatchMapping("/updateAvatar")
    public String updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        if (userService.updateAvatar(avatarUrl)) {
            return new SuccessMessage<>("更新成功").getMessage();
        } else {
            return new ErrorMessage("更新失败，请稍后尝试").getMessage();
        }
    }

    @PatchMapping("/updatePwd")
    public String updatePassword(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
        String oldPwd = params.get("oldPassword");
        String newPwd = params.get("newPassword");
        String rePwd = params.get("rePassword");

        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return new ErrorMessage("缺少必要的参数").getMessage();
        }

        // 验证原密码
        Map<String, Object> map = ThreadLocalUtil.get();
        Long id = (Long) map.get("id");
        User loginUser = userService.findById(id);
        if (!Md5Util.checkPassword(oldPwd, loginUser.getPassword())) {
            return new ErrorMessage("原密码错误").getMessage();
        }

        if (!rePwd.equals(newPwd)) {
            return new ErrorMessage("两次填写的新密码不一致").getMessage();
        }

        if (!userService.updatePwd(loginUser, newPwd)) {
            return new ErrorMessage("更新失败，请稍后尝试").getMessage();
        }

        // 删除Redis中的token
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.getOperations().delete(token);

        return new SuccessMessage<>("更新成功").getMessage();
    }

    @GetMapping("/info")
    public String userInfo(@RequestHeader("Authorization") String token) {
        Map<String, Object> map = JwtUtil.parseToken(token);
        Long id = (Long) map.get("id");

        User user = userService.findById(id);
        return new SuccessMessage<>("查询成功", user).getMessage();
    }
}
