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
    private TokenRepository tokenRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Override
    public void saveUserVerificationToken(User theUser, String verificationToken) {
        Token tk = new Token();
        tk.setToken(verificationToken);
        Account acc = accountRepository.findOneByUser(theUser).get();
        tk.setAccount(acc);
        tokenRepository.save(tk);
    }
}
