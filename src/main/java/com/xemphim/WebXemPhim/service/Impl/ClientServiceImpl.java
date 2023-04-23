package com.xemphim.WebXemPhim.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.*;
import com.xemphim.WebXemPhim.dto.mapper.FilmMapper;
import com.xemphim.WebXemPhim.dto.request.ChangeInfoRequestDTO;
import com.xemphim.WebXemPhim.dto.request.CommentRequestDTO;
import com.xemphim.WebXemPhim.dto.request.EvaluationRequestDTO;
import com.xemphim.WebXemPhim.entity.*;
import com.xemphim.WebXemPhim.repository.*;
import com.xemphim.WebXemPhim.service.ClientService;
import com.xemphim.WebXemPhim.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.*;

@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PurchasedFilmPackageRepository purchasedFilmPackageRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private FilmCategoryRepository filmCategoryRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private EpisodeRepository episodeRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private CommentRepository commentRepository;


    @Override
    public APIResponse purchase(FilmPackage filmPackage, Optional<Account> account) {
        account.get().setRole(roleRepository.findOneByRoleNameIgnoreCase("ROLE_USER"));
        accountRepository.save(account.get());
        APIResponse apiResponse = new APIResponse();
        PurchasedFilmPackage purchasedFilmPackage = new PurchasedFilmPackage();
        PurchasedFilmPackageId id = new PurchasedFilmPackageId();
        id.setAccount(account.get());
        id.setFilmPackage(filmPackage);
        purchasedFilmPackage.setPurchaseDate(new Date());
        purchasedFilmPackage.setId(id);
        purchasedFilmPackage.setExpiration_date(new Date());
        purchasedFilmPackageRepository.save(purchasedFilmPackage);
        apiResponse.setData("ok");
        return apiResponse;
    }

    @Override
    public APIResponse GetFilmByCategory(String category) {
        APIResponse apiResponse = new APIResponse();
        List<Film> films = filmCategoryRepository.findAllByIdCategory(categoryRepository.findByCategoryName(category));
        List<FilmDTO> filmDTOs = new ArrayList<>();
        for (Film f:films) {
            List<FilmCategory> filmCategories = filmCategoryRepository.findAllByIdFilm(f);
            List<Category> categories = new ArrayList<>();
            for (FilmCategory fc: filmCategories) {
                categories.add(fc.getId().getCategory());
            }
            filmDTOs.add(FilmMapper.getInstance().toFilmDTO(f,categories));
        }
        HashMap<String,Object> map = new HashMap<>();
        map.put("films",filmDTOs);
        apiResponse.setData(map);
        return apiResponse;
    }

    @Override
    public APIResponse GetFilmsByName(String filmName) {
        APIResponse apiResponse = new APIResponse();
        List<Film> films = filmRepository.findAllByFilmNameIgnoreCaseContains(filmName);
        HashMap<String,Object> map = new HashMap<>();
        List<FilmDTO> filmDTOs = new ArrayList<>();
        for (Film f:films) {
            List<FilmCategory> filmCategories = filmCategoryRepository.findAllByIdFilm(f);
            List<Category> categories = new ArrayList<>();
            for (FilmCategory fc: filmCategories) {
                categories.add(fc.getId().getCategory());
            }
            filmDTOs.add(FilmMapper.getInstance().toFilmDTO(f,categories));
        }
        map.put("films",filmDTOs);
        apiResponse.setData(map);
        return apiResponse;
    }

    @Override
    public void getInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        if (accountName != null) {
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            User user = account.getUser();
            ChangeInfoRequestDTO info = new ChangeInfoRequestDTO();
            info.setUserName(user.getUserName());
            info.setSex(user.getSex());
            info.setEmail(user.getEmail());
            info.setBirthDate(user.getBirthdate());
            info.setPhoneNumber(user.getPhoneNumber());
            APIResponse apiResponse = new APIResponse();
            HashMap<String,Object> hm = new HashMap<>();
            hm.put("info",info);
            apiResponse.setData(hm);
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public void getPackages(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    @Override
    public void evaluate(@RequestBody EvaluationRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        if (accountName != null) {
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            Evaluation evaluation = new Evaluation();
            EvaluationId id = new EvaluationId();
            id.setFilm(filmRepository.findOneByFilmNameIgnoreCase(requestDTO.getFilmName()));
            id.setAccount(account);
            evaluation.setId(id);
            evaluation.setStarNumber(requestDTO.getRating());
            evaluation.setComment(requestDTO.getComment());
            evaluationRepository.save(evaluation);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
        else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Fail");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public void comment(CommentRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        if (accountName != null) {
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            Comment comment = new Comment();
            CommentId id = new CommentId();
            id.setAccount(account);
            id.setFilm(filmRepository.findOneByFilmNameIgnoreCase(requestDTO.getFilmName()));
            id.setCommentLevel(requestDTO.getLever());
            comment.setId(id);
            comment.setCommentDate(new Date());
            comment.setCommentContent(requestDTO.getContent());
            commentRepository.save(comment);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
        else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Fail");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public APIResponse GetDetailFilm(String filmName) {
        APIResponse apiResponse = new APIResponse();
        Film film = filmRepository.findOneByFilmNameIgnoreCase(filmName);
        List<FilmCategory> filmCategories = filmCategoryRepository.findAllByIdFilm(film);
        List<Category> categories = new ArrayList<>();
        for (FilmCategory fc: filmCategories) {
            categories.add(fc.getId().getCategory());
        }
        List<Episode> episodes = episodeRepository.findByFilm(film);

        if (film == null) {
            apiResponse.setData("Movie not found!");
        } else {
            FilmDTO filmDTO = FilmMapper.getInstance().toDetailFilmDTO(film, categories, episodes);
            apiResponse.setData(filmDTO);
        }
        return apiResponse;
    }

    @Override
    public APIResponse GetAllUser() {
        return null;
    }

}
