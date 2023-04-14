package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmRepository extends JpaRepository<Film, Integer>{

    Film findOneByFilmNameIgnoreCase(String filmName);
}
