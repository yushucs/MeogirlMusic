package com.example.meogirlmusic.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.example.meogirlmusic.entity.Singer;
import com.example.meogirlmusic.mapper.SingerMapper;
import com.example.meogirlmusic.service.SingerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SingerServiceImpl implements SingerService {

    private final SingerMapper singerMapper;

    @Autowired
    SingerServiceImpl(SingerMapper singerMapper) {
        this.singerMapper = singerMapper;
    }

    @Override
    public boolean addSinger(Singer singer) {
        return singerMapper.insert(singer) > 0;
    }

    @Override
    public boolean updateSinger(Singer singer) {
        return singerMapper.updateById(singer) > 0;
    }

    @Override
    public boolean deleteSinger(Singer singer) {
        return singerMapper.deleteById(singer) > 0;
    }

    @Override
    public List<Singer> getSingerList(String name) {
        LambdaQueryWrapper<Singer> wrapper = new LambdaQueryWrapper<Singer>()
                .likeRight(Singer::getName, name);
        return singerMapper.selectList(wrapper);
    }


}
