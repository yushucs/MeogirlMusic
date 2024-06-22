package com.example.meogirlmusic.service;

import com.example.meogirlmusic.entity.Singer;

import java.util.List;

public interface SingerService {
    boolean addSinger(Singer singer);
    boolean updateSinger(Singer singer);
    boolean deleteSinger(Singer singer);
    List<Singer> getSingerList(String name);
}
