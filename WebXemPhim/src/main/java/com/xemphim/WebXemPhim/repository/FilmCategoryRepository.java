package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Category;
import com.xemphim.WebXemPhim.entity.Film;
import com.xemphim.WebXemPhim.entity.FilmCategory;
import com.xemphim.WebXemPhim.entity.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId> {

    List<FilmCategory> findAllByIdCategory(Category category);

    List<FilmCategory> findAllByIdFilm(Film film);

}
