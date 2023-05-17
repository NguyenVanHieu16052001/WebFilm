package com.xemphim.WebXemPhim.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.*;
import com.xemphim.WebXemPhim.dto.mapper.FilmMapper;
import com.xemphim.WebXemPhim.dto.request.CommentRequestDTO;
import com.xemphim.WebXemPhim.dto.request.EvaluationRequestDTO;
import com.xemphim.WebXemPhim.entity.*;
import com.xemphim.WebXemPhim.output.FilmPackageOutput;
import com.xemphim.WebXemPhim.repository.*;
import com.xemphim.WebXemPhim.service.ClientService;
import com.xemphim.WebXemPhim.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    private FavoriteRepository favoriteRepository;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private EvaluationRepository evaluationRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private FilmPackageRepository filmPackageRepository;


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
    public APIResponse GetFilmByCategory(int page,String category) {
        Pageable pageable = PageRequest.of(page,10);
        APIResponse apiResponse = new APIResponse();
        Page<FilmCategory> listFilmCategoriesPage = filmCategoryRepository.findAllByIdCategory(categoryRepository.findByCategoryName(category),pageable);
        List<FilmCategory> listFilmCategories = listFilmCategoriesPage.getContent();
        List<Film> films = new ArrayList<>();
        for (FilmCategory f:listFilmCategories) {
            films.add(f.getId().getFilm());
        }
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
    public APIResponse GetFilmsByName(int page, String filmName) {
        Pageable pageable = PageRequest.of(page,10);
        APIResponse apiResponse = new APIResponse();
        Page<Film> filmsPage = filmRepository.findAllByFilmNameIgnoreCaseContains(filmName,pageable);
        List<Film> films = filmsPage.getContent();
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
            user.setAccount(null);
//            ChangeInfoRequestDTO info = new ChangeInfoRequestDTO();
//            info.setUserName(user.getUserName());
//            info.setSex(user.getSex());
//            info.setEmail(user.getEmail());
//            info.setBirthdate(user.getBirthdate());
//            info.setPhoneNumber(user.getPhoneNumber());
            APIResponse apiResponse = new APIResponse();
            HashMap<String,Object> hm = new HashMap<>();
            hm.put("info",user);
            apiResponse.setData(hm);
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public void getPackages(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }
    @Override
    public void evaluate(String filmName, @RequestBody EvaluationRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        if (accountName != null) {
            Film film = filmRepository.findOneByFilmNameIgnoreCase(filmName);
            if(film == null){
                APIResponse apiResponse = new APIResponse();
                apiResponse.setData("Film not found");
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            Evaluation evaluation = new Evaluation();
            EvaluationId id = new EvaluationId();
            id.setFilm(film);
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
            apiResponse.setData("403 Forbidden Access is denied");
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
            Film film = filmRepository.findOneByFilmNameIgnoreCase(requestDTO.getFilmName());
            if(film == null){
                APIResponse apiResponse = new APIResponse();
                apiResponse.setStatus(400);
                apiResponse.setError("Film not found");
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            Comment comment = new Comment();
            comment.setFilm(film);
            comment.setAccount(account);
            comment.setCommentDate(new Date());
            comment.setCommentContent(requestDTO.getContent());
            if(requestDTO.getParentCommentID() != 0){
                Comment parent = commentRepository.findById(requestDTO.getParentCommentID()).get();
                if(parent.getFilm().getFilmId() == film.getFilmId())
                {
                    comment.setParentComment(parent);
                }
                else {
                    APIResponse apiResponse = new APIResponse();
                    apiResponse.setStatus(400);
                    apiResponse.setError("ParentID wrong");
                    new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
                }
            }
            commentRepository.save(comment);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
        else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("403 Forbidden Access is denied");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public APIResponse getHome() {
        APIResponse apiResponse = new APIResponse();
        HashMap<String, Object> map = new HashMap<>();
        List<Film> allFilm = filmRepository.findByOrderByReleaseTime();
        List<FilmDTO> allFilmDTO = new ArrayList<>();
        for (Film f : allFilm) {
            List<FilmCategory> filmCategories = filmCategoryRepository.findAllByIdFilm(f);
            List<Category> categories = new ArrayList<>();
            for (FilmCategory fc : filmCategories) {
                categories.add(fc.getId().getCategory());
            }
            allFilmDTO.add(FilmMapper.getInstance().toFilmDTO(f, categories));
        }
        if (allFilmDTO.size() > 10)
            map.put("newFilms", allFilmDTO.subList(0, 10));
        else
            map.put("newFilms", allFilmDTO.subList(0, allFilmDTO.size()));
        map.put("films", allFilmDTO);
        apiResponse.setData(map);
        return apiResponse;
    }

    @Override
    public void favorite(String filmName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        if (accountName != null) {
            Film film = filmRepository.findOneByFilmNameIgnoreCase(filmName);
            if (film == null) {
                APIResponse apiResponse = new APIResponse();
                apiResponse.setStatus(400);
                apiResponse.setError("Film not found");
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            Favorite f = new Favorite();
            FavoriteID id = new FavoriteID();
            id.setFilm(film);
            id.setAccount(account);
            f.setId(id);
            if(favoriteRepository.findByIdFilmAndIdAccount(film,account) != null ){
                favoriteRepository.delete(f);
            }
            else
                favoriteRepository.save(f);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public APIResponse GetDetailFilm(int page, String filmName) {
        Pageable pageable = PageRequest.of(page,5);
        APIResponse apiResponse = new APIResponse();
        Film film = filmRepository.findOneByFilmNameIgnoreCase(filmName);
        List<FilmCategory> filmCategories = filmCategoryRepository.findAllByIdFilm(film);
        List<Category> categories = new ArrayList<>();
        for (FilmCategory fc: filmCategories) {
            categories.add(fc.getId().getCategory());
        }
        List<Episode> episodes = episodeRepository.findByFilm(film);

        if (film == null) {
            apiResponse.setStatus(400);
            apiResponse.setError("Movie not found!");
        } else {
            Page<Object> commentsPage = commentRepository.findCommentsTree(String.valueOf(film.getFilmId()),pageable);
            List<Object> comments = commentsPage.getContent();
            List<CommentDTO> l = new ArrayList<>();
            for (Object obj: comments) {
                Object[] row = (Object[]) obj;
                int comment_id = (int) row[0];
                String account_name = (String) row[1];
                String comment_content = (String) row[2];
                int parent_comment_id;
                try {
                    parent_comment_id = (int) row[3];
                }
                catch (NullPointerException e) {
                    parent_comment_id = 0;
//                    e.fillInStackTrace();
                }
                long level = (long) row[4];
                String path = (String) row[5];
                l.add(new CommentDTO(comment_id,account_name,comment_content,parent_comment_id,level,path));
            }
            FilmDTO filmDTO = FilmMapper.getInstance().toDetailFilmDTO(film, categories, episodes,l);
            apiResponse.setData(filmDTO);
        }
        return apiResponse;
    }

    @Override
    public APIResponse GetAllUser() {
        return null;
    }
    @Override
    public List<FilmPackageOutput> getFilmPackages() {
        List<Object[]> objects = new ArrayList<>();
        List<FilmPackageOutput> filmPackageOutputs = new ArrayList<>();
        objects = filmPackageRepository.getFilmPackages();
        for (Object[] object: objects) {
            FilmPackageOutput filmPackageOutput = new FilmPackageOutput();
            filmPackageOutput.setDiscountRate((Float) object[0]);
            filmPackageOutput.setUsedTime((Integer) object[1]);
            filmPackageOutput.setPrice((Integer) object[2]);
            filmPackageOutputs.add(filmPackageOutput);
        }
        return filmPackageOutputs;
    }

    @Override
    public void getNotifyPagination(Integer page, HttpServletRequest request, HttpServletResponse response) throws IOException {
        Pageable pageable = (Pageable) PageRequest.of(page,3);
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        Page<Episode> episodesPage = episodeRepository.findEpisodesFavoritePagination(accountName,pageable);
        List<Episode> episodes = episodesPage.getContent();
        List<NotifyDTO> notifyDTOS = new ArrayList<>();
        NotifyDTO dto;
        for (Episode e:episodes) {
            dto = new NotifyDTO();
            dto.setImage(e.getFilm().getFilmPosterPath());
            dto.setContent(e.getFilm().getFilmName() + " - New Episode");
            dto.setNotifications(e.getTitle());
            dto.setRelease_Days(e.getCreAt());
            notifyDTOS.add(dto);
        }
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(notifyDTOS);
        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
    }
}
