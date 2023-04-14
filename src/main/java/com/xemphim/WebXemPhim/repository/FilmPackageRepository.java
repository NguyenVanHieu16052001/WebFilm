package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.FilmPackage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmPackageRepository extends JpaRepository<FilmPackage, Integer>{

    FilmPackage findOneByfilmPackageId(String id);
}
