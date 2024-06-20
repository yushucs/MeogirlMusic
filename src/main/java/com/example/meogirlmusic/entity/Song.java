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
public class Song {
    // 歌曲ID 主键
    @TableId(value = "song_id", type = IdType.ASSIGN_UUID)
    private Long id;
    // 歌手ID 外键
    private Long singerId;
    // 专辑ID 外键
    private Long albumId;
    // 歌曲名
    private String name;
    // 歌手名
    private String singer;
    // 专辑名
    private String album;
    // 歌曲封面
    private String cover;
    // 歌词
    private String lyric;
    // 歌曲超链接
    private String url;
    // 创建日期
    private LocalDateTime createTime;
    // 更新日期
    private LocalDateTime updateTime;
}
