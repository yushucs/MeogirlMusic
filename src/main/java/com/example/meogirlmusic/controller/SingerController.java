package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.entity.Singer;
import com.example.meogirlmusic.service.SingerService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/singers")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class SingerController {

    private final SingerService singerService;

    @PostMapping
    public String register(@RequestBody Singer singer) {
        if (singerService.save(singer)) {
            return new SuccessMessage<>("歌手注册成功").getMessage();
        } else {
            return new ErrorMessage("歌手注册失败，请稍后尝试").getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (singerService.removeById(id)) {
            return new SuccessMessage<>("歌手注销成功").getMessage();
        } else {
            return new ErrorMessage("歌手注销失败，请稍后尝试").getMessage();
        }
    }

    @GetMapping("/{id}")
    public Singer get(@PathVariable Long id) {
        return singerService.getById(id);
    }

    @GetMapping
    public String getAll(@RequestParam List<Long> ids) {
        return new SuccessMessage<>("查询成功", singerService.listByIds(ids)).getMessage();
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Singer singer) {
        if (singerService.updateById(singer)) {
            return new SuccessMessage<>("更新成功").getMessage();
        } else {
            return new ErrorMessage("歌手更新失败，请稍后尝试").getMessage();
        }
    }

}
