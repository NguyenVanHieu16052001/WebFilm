package com.xemphim.WebXemPhim.service;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public interface JwtService {
    String generateToken(UserDetails userDetails);
    String generateToken( Map<String, Object> extraClaims, UserDetails userDetails);
    String generateRefreshToken( UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
    String extractAccountName(String token);

    String validateToken(String token);
}
