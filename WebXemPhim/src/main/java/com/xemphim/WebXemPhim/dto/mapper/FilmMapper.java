package com.xemphim.WebXemPhim.dto.mapper;

import com.xemphim.WebXemPhim.dto.FilmDTO;
import com.xemphim.WebXemPhim.entity.*;
import com.xemphim.WebXemPhim.repository.FilmCategoryRepository;
import com.xemphim.WebXemPhim.repository.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilmMapper {
    private static FilmMapper INSTANCE;
    public static FilmMapper getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new FilmMapper();
        }
        return INSTANCE;
    }
    public FilmDTO toFilmDTO(Film film, List<Category> categories){
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmName(film.getFilmName());
        filmDTO.setFilmPosterPath(film.getFilmPosterPath());
        ArrayList<String> categories2 = new ArrayList<>();
        for (Category c : categories) {
            categories2.add(c.getCategoryName());
        }
        filmDTO.setFilmCategory(categories2);
        filmDTO.setFilmRating(film.getRating());
        filmDTO.setOdd(filmDTO.getOdd());
        filmDTO.setFilmDescription(film.getFilmDescription());
        return filmDTO;
    }
    public FilmDTO toDetailFilmDTO(Film film, List<Category> categories,List<Episode> episodes, List<Object> comments){
        FilmDTO filmDTO = new FilmDTO();
        filmDTO.setFilmName(film.getFilmName());
        filmDTO.setFilmPosterPath(film.getFilmPosterPath());
        ArrayList<String> categories2 = new ArrayList<>();
        for (Category c : categories) {
            categories2.add(c.getCategoryName());
        }
        filmDTO.setFilmCategory(categories2);
        filmDTO.setFilmRating(film.getRating());
        //Detail
        filmDTO.setFilmDescription(film.getFilmDescription());
        filmDTO.setOdd(film.isOddFilm());
        filmDTO.setTrailerPath(film.getTrailerPath());
        filmDTO.setRelease_time(film.getReleaseTime());
        Map<String,String> epi = new HashMap<>();
        for (Episode e : episodes) {
            epi.put(e.getTitle(),e.getEpisodePath());
        }
        filmDTO.setComment(comments);
        filmDTO.setEpisodes(epi);
        return filmDTO;
    }
}
