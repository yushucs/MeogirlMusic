package com.example.meogirlmusic.service.impl;

import com.example.meogirlmusic.entity.Album;
import com.example.meogirlmusic.mapper.AlbumMapper;
import com.example.meogirlmusic.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumMapper albumMapper;

    @Autowired
    public AlbumServiceImpl(AlbumMapper albumMapper) {
        this.albumMapper = albumMapper;
    }

    @Override
    public boolean addAlbum(Album album) {
        return albumMapper.insert(album) > 0;
    }
}
