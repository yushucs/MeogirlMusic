package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.entity.Album;
import com.example.meogirlmusic.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/albums")
@RequiredArgsConstructor(onConstructor_ = {@Autowired})
public class AlbumController {

    private final AlbumService albumService;

    @PostMapping
    public String addAlbum(Album album) {
        if (albumService.save(album)) {
            return new SuccessMessage<>("专辑注册成功").getMessage();
        } else {
            return new ErrorMessage("专辑注册失败，请稍后尝试").getMessage();
        }
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        if (albumService.removeById(id)) {
            return new SuccessMessage<>("专辑注销成功").getMessage();
        } else {
            return new ErrorMessage("专辑注销失败，请稍后尝试").getMessage();
        }
    }

    @GetMapping("/{id}")
    public Album get(@PathVariable Long id) {
        return albumService.getById(id);
    }

    @GetMapping
    public String getAll(@RequestParam List<Long> ids) {
        return new SuccessMessage<>("查询成功", albumService.listByIds(ids)).getMessage();
    }

    @PutMapping("/{id}")
    public String update(@RequestBody Album album) {
        if (albumService.updateById(album)) {
            return new SuccessMessage<>("更新成功").getMessage();
        } else {
            return new ErrorMessage("歌手更新失败，请稍后尝试").getMessage();
        }
    }
}
