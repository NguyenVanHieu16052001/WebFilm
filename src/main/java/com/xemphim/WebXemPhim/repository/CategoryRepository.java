package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer>{
    Category findByCategoryName(String category);

}
