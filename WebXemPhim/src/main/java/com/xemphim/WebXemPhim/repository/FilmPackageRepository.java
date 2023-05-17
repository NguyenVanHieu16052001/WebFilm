package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.FilmPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FilmPackageRepository extends JpaRepository<FilmPackage, Integer>{
    @Query(value = "CALL get_film_packages", nativeQuery = true)
    List<Object[]> getFilmPackages();
    FilmPackage findOneByfilmPackageId(String id);
}
