package com.example.meogirlmusic.controller;

import com.example.meogirlmusic.common.net.ErrorMessage;
import com.example.meogirlmusic.common.net.SuccessMessage;
import com.example.meogirlmusic.common.utils.AliOssUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("/image")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String imageName = file.getOriginalFilename();

        if (imageName == null || imageName.isEmpty()) {
            return new ErrorMessage("文件名为空，请重试").getMessage();
        }

        // 获取最后一个点的位置
        int dotIndex = imageName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == 0 || dotIndex == imageName.length() - 1) {
            return new ErrorMessage("非法图片名，请重试").getMessage();
        }

        // 分离前缀和后缀
        String prefix = imageName.substring(0, dotIndex);
        String suffix = imageName.substring(dotIndex);

        // 生成新的文件名
        String objectName = "images/" + prefix + "_" + UUID.randomUUID() + suffix;

        // 上传文件并获取URL
        String url = AliOssUtil.uploadFile(objectName, file.getInputStream());

        return new SuccessMessage<>("上传成功", url).getMessage();
    }

    @PostMapping("/music")
    public String uploadMusic(@RequestParam("file") MultipartFile file) throws IOException {
        String musicName = file.getOriginalFilename();

        if (musicName == null || musicName.isEmpty()) {
            return new ErrorMessage("文件名为空，请重试").getMessage();
        }

        // 获取最后一个点的位置
        int dotIndex = musicName.lastIndexOf('.');

        if (dotIndex == -1 || dotIndex == 0 || dotIndex == musicName.length() - 1) {
            return new ErrorMessage("非法文件名，请重试").getMessage();
        }

        // 分离前缀和后缀
        String prefix = musicName.substring(0, dotIndex);
        String suffix = musicName.substring(dotIndex);

        // 生成新的文件名
        String objectName = "music/" + prefix + "_" + UUID.randomUUID() + suffix;

        // 上传文件并获取URL
        String url = AliOssUtil.uploadFile(objectName, file.getInputStream());

        return new SuccessMessage<>("上传成功", url).getMessage();
    }
}
