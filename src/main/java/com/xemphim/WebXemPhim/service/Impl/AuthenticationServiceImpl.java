package com.xemphim.WebXemPhim.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.AccountDTO;
import com.xemphim.WebXemPhim.dto.SignInRequestDTO;
import com.xemphim.WebXemPhim.dto.SignUpRequestDTO;
import com.xemphim.WebXemPhim.dto.mapper.AccountMapper;
import com.xemphim.WebXemPhim.entity.Account;
import com.xemphim.WebXemPhim.entity.Role;
import com.xemphim.WebXemPhim.entity.Token;
import com.xemphim.WebXemPhim.entity.User;
import com.xemphim.WebXemPhim.repository.AccountRepository;
import com.xemphim.WebXemPhim.repository.RoleRepository;
import com.xemphim.WebXemPhim.repository.TokenRepository;
import com.xemphim.WebXemPhim.repository.UserRepository;
import com.xemphim.WebXemPhim.service.AuthenticationService;
import com.xemphim.WebXemPhim.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
  @Autowired
  private AccountRepository repository;
  @Autowired
  private TokenRepository tokenRepository;
  @Autowired
  private RoleRepository roleRepository;
  @Autowired
  private AccountRepository accountRepository;
  @Autowired
  private UserRepository userRepository;
  @Autowired
  private  PasswordEncoder passwordEncoder;
  @Autowired
  private JwtService jwtService;
  @Autowired
  private  AuthenticationManager authenticationManager;
  public APIResponse register(SignUpRequestDTO request) {
    APIResponse apiResponse = new APIResponse();
    AccountDTO accountDTO = null;
    Role role = roleRepository.findOneByRoleNameIgnoreCase("ROLE_CLIENT");
    User user = new User();
    user.setUserName(request.getAccountName());
    user.setEmail(request.getEmail());
    user.setPhoneNumber(request.getPhoneNumber());
    user.setSex(0);
    Date date = new GregorianCalendar(2001, Calendar.MAY, 16).getTime();
    user.setBirthdate(date);
    try {
      userRepository.save(user);
      Account account = new Account();
      account.setAccountName(request.getAccountName());
      account.setPassword(passwordEncoder.encode(request.getPassword()));
      account.setUser(userRepository.findOneByEmailAndPhoneNumber(request.getEmail(), request.getPhoneNumber()));
      account.setRole(role);
      accountRepository.save(account);
      accountDTO = AccountMapper.getInstance().toDTO(account);
    } catch (Exception e) {
      apiResponse.setError(e.getMessage());
    }
    apiResponse.setData(accountDTO);
    return apiResponse;
  }
  public APIResponse authenticate(SignInRequestDTO request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.getAccountName(), request.getPassword())
    );
    Account account = repository.findOneByAccountName(request.getAccountName())
        .orElseThrow();
    var jwtToken = jwtService.generateToken(account);
    var refreshToken = jwtService.generateRefreshToken(account);
    revokeAllUserTokens(account);
    saveUserToken(account, refreshToken);
    APIResponse apiResponse = new APIResponse();
    apiResponse.setData(jwtToken);
    return apiResponse;
  }

  private void saveUserToken(Account account, String jwtToken) {
    Token token = new Token();
    token.setAccount(account);
    token.setToken(jwtToken);
    token.setExpired(false);
    token.setRevoked(false);
    tokenRepository.save(token);
  }

  private void revokeAllUserTokens(Account account) {
    var validUserTokens = tokenRepository.findAllValidTokenByAccount(account);
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String jwt;
    final String accountName;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    jwt = authHeader.substring(7);
    accountName = jwtService.extractAccountName(jwt);
    if (accountName != null) {
      var account = this.repository.findOneByAccountName(accountName)
              .orElseThrow();
      var  refreshToken = tokenRepository.findFirstByAccountOrderByIdDesc(repository.findOneByAccountName(accountName));
      if(!refreshToken.get().isExpired() && !refreshToken.get().isRevoked()) {
        var accessToken = jwtService.generateToken(account);
        APIResponse apiResponse = new APIResponse();
        apiResponse.setData(accessToken);
        new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
      }
    }
  }
}
