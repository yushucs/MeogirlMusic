package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.entity.Singer;
import com.example.meogirlmusic.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/singer")
public class SingerController {

    private final SingerService singerService;

    @Autowired
    public SingerController(SingerService singerService) {
        this.singerService = singerService;
    }

    @PostMapping("/add")
    public String register(Singer singer) {
        if (singerService.addSinger(singer)) {
            return new SuccessMessage<>("歌手注册成功").getMessage();
        } else {
            return new ErrorMessage("歌手注册失败，请稍后尝试").getMessage();
        }
    }

    @DeleteMapping("/delete")
    public String delete(Singer singer) {
        if (singerService.deleteSinger(singer)) {
            return new SuccessMessage<>("歌手注销成功").getMessage();
        } else {
            return new ErrorMessage("歌手注销失败，请稍后尝试").getMessage();
        }
    }
}
