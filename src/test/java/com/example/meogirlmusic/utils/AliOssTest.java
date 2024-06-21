package com.example.meogirlmusic.utils;


import com.example.meogirlmusic.common.utils.AliOssUtil;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.UUID;


public class AliOssTest {

    @Test
    public void uploadImage() throws FileNotFoundException {
        String imageName = "star_rail.png";

        File file = new File("图片路径");


        // 获取最后一个点的位置
        int dotIndex = imageName.lastIndexOf('.');

        // 分离前缀和后缀
        String prefix = imageName.substring(0, dotIndex);
        String suffix = imageName.substring(dotIndex);

        // 生成新的文件名
        String objectName = "images/" + prefix + UUID.randomUUID() + suffix;

        // 上传文件并获取URL
        String url = AliOssUtil.uploadFile(objectName, new FileInputStream(file));
        System.out.println(url);
    }

    @Test
    public void uploadMusic() throws FileNotFoundException {
        String musicName = "audio.mp3";

        File file = new File("音频路径");


        // 获取最后一个点的位置
        int dotIndex = musicName.lastIndexOf('.');

        // 分离前缀和后缀
        String prefix = musicName.substring(0, dotIndex);
        String suffix = musicName.substring(dotIndex);

        // 生成新的文件名
        String objectName = "images/" + prefix + UUID.randomUUID() + suffix;

        // 上传文件并获取URL
        String url = AliOssUtil.uploadFile(objectName, new FileInputStream(file));
        System.out.println(url);
    }
}
