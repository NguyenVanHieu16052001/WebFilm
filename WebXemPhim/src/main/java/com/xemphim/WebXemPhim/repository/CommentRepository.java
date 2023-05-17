package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Comment;
import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


    List<Comment> findAllByFilm(Film film);


    @Query(value = "call web_phim.get_comments_for_film(:id);", nativeQuery = true)
    List<Object> findCommentsTree(@Param("id") String id);

}
