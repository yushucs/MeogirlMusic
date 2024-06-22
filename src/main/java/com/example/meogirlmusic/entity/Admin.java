package com.example.meogirlmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    // 管理员ID
    @TableId(type = IdType.AUTO)
    private Long id;
    // 管理员名
    private String username;
    // 管理员邮箱
    private String email;
    // 管理员密码
    @JsonIgnore
    private String password;
}
