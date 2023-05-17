package com.xemphim.WebXemPhim.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.CreEpisodeRequestDTO;
import com.xemphim.WebXemPhim.dto.request.CreFilmRequestDTO;
import com.xemphim.WebXemPhim.service.AdminService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("film/new")
    public void filmUpload(
            @ModelAttribute CreFilmRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response
            ) throws IOException {
        adminService.creFilm(requestDTO, request, response);
    }
    @PostMapping("{filmName}/episode/new")
    public void episodesUpload(
            @PathVariable(value="filmName") String filmName, @ModelAttribute CreEpisodeRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response
    ) throws IOException {
        if(requestDTO.getTitle().isBlank() || requestDTO.getContent().isEmpty()){
            APIResponse apiResponse = new APIResponse();
            apiResponse.setStatus(400);
            apiResponse.setError("Cannot be null");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
        adminService.creEpisodes(filmName,requestDTO, request, response);
    }
    @GetMapping("users")
    public ResponseEntity<APIResponse> getAccounts() {
        APIResponse apiResponse = adminService.getUsers();
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
