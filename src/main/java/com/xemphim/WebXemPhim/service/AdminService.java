package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.CreFilmRequestDTO;
import com.xemphim.WebXemPhim.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;

public interface AdminService {
    User addUser(User user);

    APIResponse getUsers();
    void creFilm(@RequestBody CreFilmRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException;

}
