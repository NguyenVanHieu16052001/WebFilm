package com.xemphim.WebXemPhim.repository;


import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.Favorite;
import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FavoriteRepository extends JpaRepository<Favorite, Integer> {
    Favorite findByIdFilmAndIdAccount(Film filmName, Account accountName);

}
