package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.CommentRequestDTO;
import com.xemphim.WebXemPhim.dto.request.EvaluationRequestDTO;
import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.FilmPackage;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Optional;

public interface ClientService {
    APIResponse GetDetailFilm(String nameFilm);
    APIResponse GetAllUser();
    APIResponse purchase(FilmPackage filmPackage, Optional<Account> account);
    APIResponse GetFilmByCategory(String category);
    APIResponse GetFilmsByName(String category);

    void getInfo(HttpServletRequest request, HttpServletResponse response) throws IOException;

    void getPackages(HttpServletRequest request, HttpServletResponse response) throws IOException;
    void evaluate(String filmName, @RequestBody EvaluationRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response)throws IOException;

    void comment(String filmName, CommentRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException;

    APIResponse getHome();
}
