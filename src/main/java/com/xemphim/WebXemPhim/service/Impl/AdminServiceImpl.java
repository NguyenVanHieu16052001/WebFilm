package com.xemphim.WebXemPhim.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.CreEpisodeRequestDTO;
import com.xemphim.WebXemPhim.dto.request.CreFilmRequestDTO;
import com.xemphim.WebXemPhim.entity.Film;
import com.xemphim.WebXemPhim.entity.User;
import com.xemphim.WebXemPhim.repository.FilmRepository;
import com.xemphim.WebXemPhim.repository.UserRepository;
import com.xemphim.WebXemPhim.service.AdminService;
import com.xemphim.WebXemPhim.service.FileService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Value("${project.video}")
    private String path;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FileService fileService;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public APIResponse getUsers() {
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(userRepository.findAll());
        return apiResponse;
    }

    @Override
    public void creFilm(CreFilmRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String poster = fileService.uploadFile(path, requestDTO.getPoster());
            String trailer = fileService.uploadFile(path, requestDTO.getTrailer());
            List<String> episodes = new ArrayList<>();
            for (CreEpisodeRequestDTO e : requestDTO.getEpisodeRequests()) {
                episodes.add(fileService.uploadFile(path.concat("content/"), e.getContent()));
            }
            System.out.print(poster + "\n" + trailer + "\n" + episodes);
            Film film = new Film();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        } catch (Exception e) {
            e.printStackTrace();
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }



}
