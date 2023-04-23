package com.xemphim.WebXemPhim.service.Impl;

import com.xemphim.WebXemPhim.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String path, MultipartFile file) throws IOException {
        String name = file.getOriginalFilename();
        String randomID = UUID.randomUUID().toString();
        String fileName = randomID.concat(name.substring(name.lastIndexOf(".")));
        String filePath = path + File.separator + fileName;
        File f = new File(path);
        if (!f.exists()) {
            f.mkdir();
        }
        Files.copy(file.getInputStream(), Paths.get(filePath));
        String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(path)
                .path(fileName)
                .toUriString();
        return fileDownloadUri;
    }

    @Override
    public Resource loadFileAsResource(String fileName, String path) {
        try {
            File file = ResourceUtils.getFile(path + fileName);
            Path filePath = file.toPath();
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
