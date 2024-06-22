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
public class Playlist {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    private String introduction;
    private String cover;
    // 创建日期
    private LocalDateTime createTime;
    // 更新日期
    private LocalDateTime updateTime;
}
