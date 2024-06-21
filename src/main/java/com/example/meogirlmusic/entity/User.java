package com.example.meogirlmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    // 用户ID
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    // 用户名
    private String username;
    // 用户邮箱
    private String email;
    // 用户密码
    @JsonIgnore
    private String password;
    // 性别
    private String gender;
    // avatar
    private String avatar;
    // 用户介绍
    private String introduction;
    // 创建日期
    private LocalDateTime createTime;
    // 更新日期
    private LocalDateTime updateTime;
}
