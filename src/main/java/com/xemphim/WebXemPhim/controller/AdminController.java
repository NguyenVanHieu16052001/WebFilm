package com.xemphim.WebXemPhim.controller;

import com.xemphim.WebXemPhim.common.APIResponse;
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

@RestController
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("film/new")
    public void fileUpload(
            @ModelAttribute CreFilmRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response
            ) throws IOException {
        adminService.creFilm(requestDTO, request, response);
    }
    @GetMapping("users")
    public ResponseEntity<APIResponse> getAccounts() {
        APIResponse apiResponse = adminService.getUsers();
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
