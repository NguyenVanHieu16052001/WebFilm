package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Comment;
import com.xemphim.WebXemPhim.entity.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {


    List<Comment> findAllByFilm(Film film);


    @Query(value = "WITH RECURSIVE comments_cte(comment_id, account_name, comment_content, parent_comment_id, level, path) AS ( " +
            "SELECT comment_id, account_name, comment_content, parent_comment_id, 0, CAST(comment_id AS CHAR(200)) " +
            "FROM comments WHERE parent_comment_id IS NULL and film_id = :id " +
            "UNION ALL " +
            "SELECT c.comment_id,c.account_name, c.comment_content, c.parent_comment_id, p.level + 1, CONCAT(p.path, '-', c.comment_id) " +
            "FROM comments c JOIN comments_cte p ON c.parent_comment_id = p.comment_id) " +
            " SELECT * FROM comments_cte ORDER BY path;",
            countQuery = "WITH RECURSIVE comments_cte(comment_id, account_name, comment_content, parent_comment_id, level, path) AS ( " +
                    "SELECT comment_id, account_name, comment_content, parent_comment_id, 0, CAST(comment_id AS CHAR(200)) " +
                    "FROM comments WHERE parent_comment_id IS NULL and film_id = :id " +
                    "UNION ALL " +
                    "SELECT c.comment_id,c.account_name, c.comment_content, c.parent_comment_id, p.level + 1, CONCAT(p.path, '-', c.comment_id) " +
                    "FROM comments c JOIN comments_cte p ON c.parent_comment_id = p.comment_id) " +
                    " SELECT COUNT(*) FROM comments_cte ORDER BY path;",
            nativeQuery = true)
    Page<Object> findCommentsTree(@Param("id") String id, Pageable pageable);

}
