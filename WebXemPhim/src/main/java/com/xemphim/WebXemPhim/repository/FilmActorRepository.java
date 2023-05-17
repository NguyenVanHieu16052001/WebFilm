package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.FilmActor;
import com.xemphim.WebXemPhim.entity.FilmActorId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorId>{

}
