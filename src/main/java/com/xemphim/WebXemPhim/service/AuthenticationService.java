package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.ChangeInfoRequestDTO;
import com.xemphim.WebXemPhim.dto.ChangePasswordRequestDTO;
import com.xemphim.WebXemPhim.dto.SignInRequestDTO;
import com.xemphim.WebXemPhim.dto.SignUpRequestDTO;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
  APIResponse register(SignUpRequestDTO request);
  APIResponse authenticate(SignInRequestDTO request);
  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
  void changePassword(ChangePasswordRequestDTO requestDTO,HttpServletRequest request,HttpServletResponse response)throws IOException;
  void changeInfo(ChangeInfoRequestDTO requestDTO, HttpServletRequest request,HttpServletResponse response)throws IOException;
}
