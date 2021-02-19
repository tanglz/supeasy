package com.uoit.network.supeasy.service;

import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

public interface ImageService {
    void init();

    void save(MultipartFile file);

    Path load(String fileName);

}
