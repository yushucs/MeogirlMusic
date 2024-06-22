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
public class Singer {
    @TableId(type = IdType.ASSIGN_ID)
    private Long id;
    private String name;
    // 歌手主页URL
    private String home;
    private Integer age;
    private String gender;
    private LocalDate birthday;
    private String avatar;
    private String introduction;
    private String createdTime;
    private LocalDateTime updateTime;
}
