package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Episode;
import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer>{

    List<Episode> findByFilm(Film film);
}
