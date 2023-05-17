package com.xemphim.WebXemPhim.service;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface AuthenticationService {
  APIResponse register(SignUpRequestDTO request,final HttpServletRequest httpServletRequest);
  APIResponse authenticate(SignInRequestDTO request);
  void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
  void changePassword(ChangePasswordRequestDTO requestDTO,HttpServletRequest request,HttpServletResponse response)throws IOException;
  void changeInfo(ChangeInfoRequestDTO requestDTO, HttpServletRequest request,HttpServletResponse response)throws IOException;

  APIResponse verifyEmail(String token);

  APIResponse resetPass(ResetPasswordRequestDTO requestDTO, HttpServletRequest request);
}
