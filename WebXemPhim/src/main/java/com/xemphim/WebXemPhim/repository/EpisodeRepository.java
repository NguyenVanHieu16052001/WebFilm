package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Episode;
import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer>{

    List<Episode> findByFilm(Film film);

    @Query(value = "call web_phim.get_episodes_by_favorites(:accountName);", nativeQuery = true)
    List<Episode> findEpisodesFavorite(@Param("accountName") String accountName);
}
