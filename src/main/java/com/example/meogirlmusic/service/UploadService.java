package com.example.meogirlmusic.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    public String uploadMusic(MultipartFile file) throws IOException;

    public String uploadImage(MultipartFile file) throws IOException;
}
