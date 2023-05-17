package com.xemphim.WebXemPhim.controller;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.NotifyDTO;
import com.xemphim.WebXemPhim.dto.request.ChangeInfoRequestDTO;
import com.xemphim.WebXemPhim.dto.request.ChangePasswordRequestDTO;
import com.xemphim.WebXemPhim.dto.request.CommentRequestDTO;
import com.xemphim.WebXemPhim.dto.request.EvaluationRequestDTO;
import com.xemphim.WebXemPhim.output.FilmPackageOutput;
import com.xemphim.WebXemPhim.repository.AccountRepository;
import com.xemphim.WebXemPhim.repository.FilmPackageRepository;
import com.xemphim.WebXemPhim.service.AuthenticationService;
import com.xemphim.WebXemPhim.service.ClientService;
import com.xemphim.WebXemPhim.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("home")
public class HomeController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private FilmPackageRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AuthenticationService authenticationService;

    @GetMapping()
    public ResponseEntity<APIResponse> home() {
        APIResponse apiResponse = clientService.getHome();
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }

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
    @GetMapping("film/category/{category}/{pageNumber}")
    public ResponseEntity<APIResponse> getFilmByCategory(@PathVariable(value="category") String category,@PathVariable(value="pageNumber") int page) {
        APIResponse apiResponse = clientService.GetFilmByCategory(page,category);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @GetMapping("film/detail/{filmName}/{pageNumber}")
    public ResponseEntity<APIResponse> getDetailFilm(@PathVariable(value="filmName") String filmName,@PathVariable(value="pageNumber") int page) {
        APIResponse apiResponse = clientService.GetDetailFilm(page,filmName);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @GetMapping("film/search/{filmName}/{pageNumber}")
    public ResponseEntity<APIResponse> getFilmsByName(@PathVariable(value="filmName") String filmName,@PathVariable(value="pageNumber") int page) {
        APIResponse apiResponse = clientService.GetFilmsByName(page,filmName);
        return ResponseEntity
                .status(apiResponse.getStatus())
                .body(apiResponse);
    }
    @GetMapping("info")
    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        clientService.getInfo(request, response);
    }
    @PostMapping("info/change-password")
    public void changePassword(@RequestBody ChangePasswordRequestDTO requestDTO,HttpServletRequest request,HttpServletResponse response) throws IOException  {
        authenticationService.changePassword(requestDTO, request, response);
    }
    @PostMapping("info/change-info")
    public void changeInfo(@RequestBody ChangeInfoRequestDTO requestDTO, HttpServletRequest request,HttpServletResponse response) throws IOException {
        authenticationService.changeInfo(requestDTO, request,response);
    }
    @GetMapping("info/status-pricing")
    public void getPackages(HttpServletRequest request, HttpServletResponse response) throws IOException  {
        clientService.getPackages(request, response);
    }
    @PostMapping("film/evaluate/{filmName}")
    public void evaluate(@PathVariable(value="filmName") String filmName, @RequestBody EvaluationRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException  {
        clientService.evaluate(filmName, requestDTO,request, response);
    }
    @PostMapping("film/favorite/{filmName}")
    public void favorite(@PathVariable(value="filmName") String filmName, HttpServletRequest request, HttpServletResponse response) throws IOException  {
        clientService.favorite(filmName,request, response);
    }
    @PostMapping("film/comment")
    public void comment( @RequestBody CommentRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException  {
        clientService.comment(requestDTO,request, response);
    }
    @GetMapping("info/notify/{pageNumber}")
    public void getNotifyPagination(@PathVariable Integer pageNumber, HttpServletRequest request, HttpServletResponse response) throws IOException  {
        clientService.getNotifyPagination(pageNumber,request, response);
    }
    @GetMapping("info/film-packages")
    public List<FilmPackageOutput> getFilmPackages(){
        return clientService.getFilmPackages();
    }
}
