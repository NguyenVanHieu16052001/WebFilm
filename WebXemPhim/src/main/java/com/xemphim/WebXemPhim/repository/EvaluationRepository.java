package com.xemphim.WebXemPhim.repository;

import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.Evaluation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface EvaluationRepository extends JpaRepository<Evaluation, Integer>{

}
