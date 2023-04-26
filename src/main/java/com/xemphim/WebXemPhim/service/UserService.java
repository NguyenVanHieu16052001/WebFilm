package com.xemphim.WebXemPhim.service;


import com.xemphim.WebXemPhim.entity.User;

public interface UserService {

    void saveUserVerificationToken(User theUser, String verificationToken);
}
