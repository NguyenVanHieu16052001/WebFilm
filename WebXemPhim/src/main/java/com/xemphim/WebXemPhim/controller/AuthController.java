package com.xemphim.WebXemPhim.controller;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.request.ResetPasswordRequestDTO;
import com.xemphim.WebXemPhim.dto.request.SignInRequestDTO;
import com.xemphim.WebXemPhim.dto.request.SignUpRequestDTO;
import com.xemphim.WebXemPhim.service.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("signUp")
    public ResponseEntity<APIResponse> signIn(@RequestBody SignUpRequestDTO signUpRequestDTO,final HttpServletRequest request) {
        APIResponse apiResponse = authenticationService.register(signUpRequestDTO,request);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @GetMapping("signUp/verifyEmail")
    public ResponseEntity<APIResponse> verifyEmail(@RequestParam("token") String token){
        APIResponse apiResponse = authenticationService.verifyEmail(token);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }


    @PostMapping("signIn")
    public ResponseEntity<APIResponse> signIn(@RequestBody SignInRequestDTO signInRequestDTO) {
        APIResponse apiResponse = authenticationService.authenticate(signInRequestDTO);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @PostMapping("password-reset-request")
    public ResponseEntity<APIResponse> resetPass(@RequestBody ResetPasswordRequestDTO requestDTO, HttpServletRequest request) {
        APIResponse apiResponse = authenticationService.resetPass(requestDTO,request);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @PostMapping("refreshToken")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.refreshToken(request, response);
    }
    @GetMapping("logout")
    public void logOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        authenticationService.logOut(request, response);
    }
}
