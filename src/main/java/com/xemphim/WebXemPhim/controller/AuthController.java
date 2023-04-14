package com.xemphim.WebXemPhim.controller;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.SignInRequestDTO;
import com.xemphim.WebXemPhim.dto.SignUpRequestDTO;
import com.xemphim.WebXemPhim.repository.AccountRepository;
import com.xemphim.WebXemPhim.service.AuthenticationService;
import com.xemphim.WebXemPhim.service.JwtService;
import com.xemphim.WebXemPhim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @Autowired
    private AuthenticationService authenticationService;
    @PostMapping("signUp")
    public ResponseEntity<APIResponse> signIn(@RequestBody SignUpRequestDTO signUpRequestDTO) {
        APIResponse apiResponse = authenticationService.register(signUpRequestDTO);
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
}
