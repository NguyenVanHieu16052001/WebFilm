package com.xemphim.WebXemPhim.service.Impl;

import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.*;
import com.xemphim.WebXemPhim.dto.mapper.AccountMapper;
import com.xemphim.WebXemPhim.dto.mapper.FilmMapper;
import com.xemphim.WebXemPhim.entity.*;
import com.xemphim.WebXemPhim.repository.*;
import com.xemphim.WebXemPhim.service.JwtService;
import com.xemphim.WebXemPhim.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private EvaluationRepository evaluationRepository;


    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }


    @Override
    public APIResponse SelectFilm(String filmName) {
        APIResponse apiResponse = new APIResponse();
        Film film = filmRepository.findOneByFilmNameIgnoreCase(filmName);
        if (film == null) {
            apiResponse.setData("Movie not found!");
        } else {
            FilmDTO filmDTO = FilmMapper.getInstance().toDTO(film);
            apiResponse.setData(filmDTO);
        }
        return apiResponse;
    }

    @Override
    public APIResponse GetAllUser() {
        APIResponse apiResponse = new APIResponse();
        List<Account> account = accountRepository.findAll();
        if (account.size() == 0) {
            apiResponse.setData("Account list is null!");
        } else {
            ArrayList<AccountDTO> accountDTOS = new ArrayList<>();
            for (Account acc : account) {
                AccountDTO accountDTO = AccountMapper.getInstance().toDTO(acc);
                accountDTOS.add(accountDTO);
            }
            apiResponse.setData(accountDTOS);
        }
        return apiResponse;
    }

    @Override
    public APIResponse GetAllFilm() {
        APIResponse apiResponse = new APIResponse();
        List<Film> films = filmRepository.findAll();
        if (films.size() == 0) {
            apiResponse.setData("Film list is null!");
        } else {
            ArrayList<FilmDTO> filmDTOS = new ArrayList<>();
            for (Film f : films) {
                FilmDTO filmDTO = FilmMapper.getInstance().toDTO(f);
                filmDTOS.add(filmDTO);
            }
            apiResponse.setData(filmDTOS);
        }
        return apiResponse;
    }

}
