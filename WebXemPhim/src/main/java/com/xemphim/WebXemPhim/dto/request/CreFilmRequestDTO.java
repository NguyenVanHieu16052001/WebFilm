package com.xemphim.WebXemPhim.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CreFilmRequestDTO {
    private String name;
    private String description;
    private int duration;
    private boolean odd;
    private int producer_id;
    private String release_time;
    private int nation_id;
    private int director_id;

    private MultipartFile poster;
    private MultipartFile trailer;
    private List<CreEpisodeRequestDTO> episodeRequests;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return duration;
    }

    public String getRelease_time() {
        return release_time;
    }

    public void setRelease_time(String release_time) {
        this.release_time = release_time;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean isOdd() {
        return odd;
    }

    public void setOdd(boolean odd) {
        this.odd = odd;
    }

    public int getProducer_id() {
        return producer_id;
    }

    public void setProducer_id(int producer_id) {
        this.producer_id = producer_id;
    }

    public int getNation_id() {
        return nation_id;
    }

    public void setNation_id(int nation_id) {
        this.nation_id = nation_id;
    }

    public int getDirector_id() {
        return director_id;
    }

    public void setDirector_id(int director_id) {
        this.director_id = director_id;
    }

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
