package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Episode;
import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EpisodeRepository extends JpaRepository<Episode, Integer>{

    List<Episode> findByFilm(Film film);


    @Query(value = "SELECT * FROM episodes JOIN favorites ON episodes.film_id = favorites.favorite_film_id WHERE favorites.account_name = :accountName AND episodes.cre_at > favorites.favorite_cre_at",
        countQuery = "SELECT COUNT(*) FROM episodes JOIN favorites ON episodes.film_id = favorites.favorite_film_id WHERE favorites.account_name = :accountName AND episodes.cre_at > favorites.favorite_cre_at",
        nativeQuery = true)
    Page<Episode> findEpisodesFavoritePagination(@Param("accountName") String accountName, Pageable pageable);
}
