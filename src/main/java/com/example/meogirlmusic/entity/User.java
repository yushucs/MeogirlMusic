package com.example.meogirlmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    // 用户ID
    @TableId(type = IdType.ASSIGN_UUID)
    private Long id;
    // 用户名
    private String username;
    // 用户密码
    private String password;
    // 创建日期
    private LocalDateTime createTime;
    // 更新日期
    private LocalDateTime updateTime;
}