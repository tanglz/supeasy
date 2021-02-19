package com.uoit.network.supeasy.service.impl;

import com.uoit.network.supeasy.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;

@Service
public class ImageServiceImpl implements ImageService {
    @Override
    public void init() {

    }

    @Override
    public void save(MultipartFile file) {

    }

    @Override
    public Path load(String fileName) {
        return null;
    }
}
