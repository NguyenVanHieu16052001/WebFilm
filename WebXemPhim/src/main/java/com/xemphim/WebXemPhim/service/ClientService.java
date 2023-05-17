package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.CommentRequestDTO;
import com.xemphim.WebXemPhim.dto.request.EvaluationRequestDTO;
import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.FilmPackage;
import com.xemphim.WebXemPhim.output.FilmPackageOutput;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.List;
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

    void comment(CommentRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException;

    APIResponse getHome();

    void favorite(String filmName, HttpServletRequest request, HttpServletResponse response) throws IOException;
    void getNotifyEpisodes(HttpServletRequest request, HttpServletResponse response) throws IOException;
    List<FilmPackageOutput> getFilmPackages();
}
