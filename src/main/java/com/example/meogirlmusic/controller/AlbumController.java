package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.entity.Album;
import com.example.meogirlmusic.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    @Autowired
    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @PostMapping("/add")
    public String addAlbum(Album album) {
        if (albumService.addAlbum(album)) {
            return new SuccessMessage<>("歌手注册成功").getMessage();
        } else {
            return new ErrorMessage("歌手注册失败，请稍后尝试").getMessage();
        }
    }
}
