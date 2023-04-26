package com.xemphim.WebXemPhim.service.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xemphim.WebXemPhim.common.APIResponse;
import com.xemphim.WebXemPhim.dto.*;
import com.xemphim.WebXemPhim.dto.request.*;
import com.xemphim.WebXemPhim.entity.*;
import com.xemphim.WebXemPhim.event.RegistrationCompleteEvent;
import com.xemphim.WebXemPhim.repository.*;
import com.xemphim.WebXemPhim.service.AuthenticationService;
import com.xemphim.WebXemPhim.service.JwtService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    FilmCategoryRepository filmCategoryRepository;

    public APIResponse register(SignUpRequestDTO request,final HttpServletRequest httpServletRequest) {
        APIResponse apiResponse = new APIResponse();
        if(userRepository.findOneByEmail(request.getEmail()) != null){
            apiResponse.setData("User already exists!");
            return apiResponse;
        }

        AccountDTO accountDTO = null;
        Role role = roleRepository.findOneByRoleNameIgnoreCase("ROLE_CLIENT");
        User user = new User();
        user.setUserName(request.getAccountName());
        user.setEmail(request.getEmail());
        user.setSex(0);
        Date date = new GregorianCalendar(2001, Calendar.MAY, 16).getTime();
        user.setBirthdate(date);
        try {
            userRepository.save(user);
            Account account = new Account();
            account.setAccountName(request.getAccountName());
            account.setPassword(passwordEncoder.encode(request.getPassword()));
            account.setUser(userRepository.findOneByEmail(request.getEmail()));
            account.setRole(role);
            accountRepository.save(account);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(httpServletRequest)));
        } catch (Exception e) {
            apiResponse.setError(e.getMessage());
        }
        apiResponse.setData("Success!  Please, check your email for to complete your registration");
        return apiResponse;
    }
    public String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }
    public APIResponse authenticate(SignInRequestDTO request) {
        APIResponse apiResponse = new APIResponse();
        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getAccountName(), request.getPassword())
            );
        } catch (DisabledException e) {
            apiResponse.setStatus(403);
            apiResponse.setError("Unverified email account. Please, check your email for to complete your registration");
            return apiResponse;
        } catch (BadCredentialsException e){
            apiResponse.setStatus(403);
            apiResponse.setError("The account name or password may be wrong!");
            return apiResponse;
        }

        Account account = accountRepository.findOneByAccountName(request.getAccountName())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(account);
        var refreshToken = jwtService.generateRefreshToken(account);
        revokeAllUserTokens(account);
        saveUserToken(account, refreshToken);
        HashMap<String, Object> map = new HashMap<>();
        map.put("accToken", jwtToken);
        apiResponse.setData(map);
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
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        if (accountName != null) {
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            var refreshToken = tokenRepository.findFirstByAccountOrderByIdDesc(accountRepository.findOneByAccountName(accountName));
            if (!refreshToken.get().isExpired() && !refreshToken.get().isRevoked()) {
                var accessToken = jwtService.generateToken(account);
                APIResponse apiResponse = new APIResponse();
                apiResponse.setData(accessToken);
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }
        }
    }

    @Override
    public void changePassword(ChangePasswordRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        final String jwt;
        final String accountName;
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            return;
        }
        jwt = authHeader.substring(7);
        accountName = jwtService.extractAccountName(jwt);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(accountName, requestDTO.getPassword())
        );
        if (accountName != null) {
            var account = this.accountRepository.findOneByAccountName(accountName)
                    .orElseThrow();
            if (requestDTO.getNewPassword().equals(requestDTO.getConfirmPassword())) {
                account.setPassword(passwordEncoder.encode(requestDTO.getNewPassword()));
                APIResponse apiResponse = new APIResponse();
                apiResponse.setData("Success");
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            } else {
                APIResponse apiResponse = new APIResponse();
                apiResponse.setData("Success");
                new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
            }
        }
    }

    @Override
    public void changeInfo(ChangeInfoRequestDTO requestDTO, HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            user.setEmail(requestDTO.getEmail());
            user.setPhoneNumber(requestDTO.getPhoneNumber());
            user.setBirthdate(requestDTO.getBirthDate());
            user.setSex(requestDTO.getSex());
            user.setUserName(requestDTO.getUserName());
            userRepository.save(user);
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Success");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        } else {
            APIResponse apiResponse = new APIResponse();
            apiResponse.setData("Fail");
            new ObjectMapper().writeValue(response.getOutputStream(), apiResponse);
        }
    }

    @Override
    public APIResponse verifyEmail(String token) {
        APIResponse apiResponse = new APIResponse();
        Token tk = tokenRepository.findByToken(token);
        if (tk.getAccount().isEnabled()){
            apiResponse.setData("This account has already been verified, please, login.");
            return apiResponse;
        }
        String verificationResult = jwtService.validateToken(token);
        if (verificationResult.equalsIgnoreCase("valid")){
            Account acc = tokenRepository.findByToken(token).getAccount();
            acc.setEnabled(true);
            accountRepository.save(acc);
            apiResponse.setData("Email verified successfully. Now you can login to your account");
            return apiResponse;
        }
        apiResponse.setData("Invalid verification token");
        return apiResponse;
    }

    @Override
    public APIResponse resetPass(ResetPasswordRequestDTO requestDTO, HttpServletRequest request) {
        APIResponse apiResponse = new APIResponse();
        try {
            User user = userRepository.findOneByEmail(requestDTO.getEmail());
            Account account = accountRepository.findOneByUser(user).get();
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
            apiResponse.setData("Please check your email for the password!");
            return apiResponse;
        }catch (NoSuchElementException e){
            apiResponse.setStatus(403);
            apiResponse.setError("Email was be wrong!");
            return apiResponse;
        }
    }
}
