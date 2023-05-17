package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Nation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NationRepository extends JpaRepository<Nation, Integer>{

    Nation findOneByNationId(int nationId);
}
