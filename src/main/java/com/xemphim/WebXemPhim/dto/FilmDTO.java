package com.xemphim.WebXemPhim.dto;

public class FilmDTO {
    private String filmName;
    private String trailerPath;
    private String filmPath;
    private String filmProducer;
    private String nation;
    private String director;

    public String getFilmName() {
        return filmName;
    }

    public void setFilmName(String filmName) {
        this.filmName = filmName;
    }

    public String getTrailerPath() {
        return trailerPath;
    }

    public void setTrailerPath(String trailerPath) {
        this.trailerPath = trailerPath;
    }

    public String getFilmPath() {
        return filmPath;
    }

    public void setFilmPath(String filmPath) {
        this.filmPath = filmPath;
    }

    public String getFilmProducer() {
        return filmProducer;
    }

    public void setFilmProducer(String filmProducer) {
        this.filmProducer = filmProducer;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
