package com.xemphim.WebXemPhim.dto.mapper;

import com.xemphim.WebXemPhim.dto.FilmDTO;
import com.xemphim.WebXemPhim.entity.Film;

public class FilmMapper {
    private static FilmMapper INSTANCE;
    public static FilmMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FilmMapper();
        }
        return INSTANCE;
    }
    public FilmDTO toDTO(Film film){
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmName(film.getFilmName());
        filmDTO.setFilmPath(film.getFilmDescription());
        filmDTO.setFilmProducer(film.getFilmProducer().getFilmProducerName());
        filmDTO.setNation(film.getNation().getNationName());
        filmDTO.setTrailerPath(film.getTrailerPath());
        filmDTO.setDirector(filmDTO.getDirector());
        return filmDTO;
    }
}
