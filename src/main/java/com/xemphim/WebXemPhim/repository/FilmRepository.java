package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;

public interface FilmRepository extends JpaRepository<Film, Integer>{

    Film findOneByFilmNameIgnoreCase(String filmName);
    ArrayList<Film> findByOrderByReleaseTime();

    List<Film> findAllByFilmNameIgnoreCaseContains(String filmName);
}
