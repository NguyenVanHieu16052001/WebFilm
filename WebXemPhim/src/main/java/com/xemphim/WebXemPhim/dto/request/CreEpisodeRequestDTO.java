package com.xemphim.WebXemPhim.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class CreEpisodeRequestDTO {
    private String title;
    private MultipartFile content;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public MultipartFile getContent() {
        return content;
    }

    public void setContent(MultipartFile content) {
        this.content = content;
    }
}
