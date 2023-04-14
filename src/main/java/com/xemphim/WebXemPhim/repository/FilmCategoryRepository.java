package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.FilmCategory;
import com.xemphim.WebXemPhim.entity.FilmCategoryId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmCategoryRepository extends JpaRepository<FilmCategory, FilmCategoryId>{

}
