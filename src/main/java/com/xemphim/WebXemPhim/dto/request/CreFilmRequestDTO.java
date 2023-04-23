package com.xemphim.WebXemPhim.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreFilmRequestDTO {
    private String name;
    private MultipartFile poster;
    private MultipartFile trailer;
    private List<CreEpisodeRequestDTO> episodeRequests;

    public MultipartFile getPoster() {
        return poster;
    }

    public void setPoster(MultipartFile poster) {
        this.poster = poster;
    }

    public MultipartFile getTrailer() {
        return trailer;
    }

    public void setTrailer(MultipartFile trailer) {
        this.trailer = trailer;
    }

    public List<CreEpisodeRequestDTO> getEpisodeRequests() {
        return episodeRequests;
    }

    public void setEpisodeRequests(List<CreEpisodeRequestDTO> episodeRequests) {
        this.episodeRequests = episodeRequests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
