package com.xemphim.WebXemPhim.service;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface FileService {
    String uploadFile(String path, MultipartFile file) throws IOException;
    Resource loadFileAsResource(String fileName, String path);
}
