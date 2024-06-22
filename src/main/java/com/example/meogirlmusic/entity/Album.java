package com.example.meogirlmusic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    // 歌手ID，外键
    private String singerId;
    private String introduction;
    private String cover;
    private String genre;
    private LocalDate releaseDate;
    // 创建日期
    private LocalDateTime createTime;
    // 更新日期
    private LocalDateTime updateTime;
}
