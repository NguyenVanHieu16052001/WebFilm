package com.xemphim.WebXemPhim.service;


import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.EvaluationRequestDTO;
import com.xemphim.WebXemPhim.entity.User;

public interface UserService {
    User addUser(User user);
    APIResponse SelectFilm(String nameFilm);
    APIResponse GetAllUser();
    APIResponse GetAllFilm();

}
