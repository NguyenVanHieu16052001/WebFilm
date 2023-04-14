package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.SignInRequestDTO;
import com.xemphim.WebXemPhim.dto.SignUpRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
  APIResponse register(SignUpRequestDTO request);
  APIResponse authenticate(SignInRequestDTO request);
  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
