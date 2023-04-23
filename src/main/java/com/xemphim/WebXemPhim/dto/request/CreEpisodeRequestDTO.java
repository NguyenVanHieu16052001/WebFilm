package com.xemphim.WebXemPhim.dto.request;

import org.springframework.web.multipart.MultipartFile;

public class CreEpisodeRequestDTO {
    private Integer numb;
    private MultipartFile content;

    public Integer getNumb() {
        return numb;
    }

    public void setNumb(Integer numb) {
        this.numb = numb;
    }

    public MultipartFile getContent() {
        return content;
    }

    public void setContent(MultipartFile content) {
        this.content = content;
    }
}
