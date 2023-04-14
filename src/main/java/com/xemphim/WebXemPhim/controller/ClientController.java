package com.xemphim.WebXemPhim.controller;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.repository.AccountRepository;
import com.xemphim.WebXemPhim.repository.FilmPackageRepository;
import com.xemphim.WebXemPhim.service.ClientService;
import com.xemphim.WebXemPhim.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("client")
public class ClientController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private FilmPackageRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping("purchase/{id}")
    public ResponseEntity<APIResponse> purchase(@PathVariable(value = "id") String id, HttpServletRequest request, HttpServletResponse response) {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String acc_Name = jwtService.extractAccountName(authHeader.substring(7));
        var account = accountRepository.findOneByAccountName(acc_Name);
        APIResponse apiResponse = clientService.purchase(repository.findOneByfilmPackageId(id),account);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
}
