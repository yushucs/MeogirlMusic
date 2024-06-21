package com.example.meogirlmusic.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadService {
    String uploadMusic(MultipartFile file) throws IOException;

    String uploadImage(MultipartFile file) throws IOException;
}
