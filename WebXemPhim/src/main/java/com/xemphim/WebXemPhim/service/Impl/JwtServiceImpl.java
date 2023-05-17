package com.xemphim.WebXemPhim.service.Impl;

import com.xemphim.WebXemPhim.service.JwtService;
import com.xemphim.WebXemPhim.service.LogoutService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtServiceImpl implements JwtService{
    @Value("${expiryDuration}")
    private long expiryDuration;

    @Value("${rf-expiryDuration}")
    private long refreshExpiryDuration;

    @Value("${secret}")
    private String secret;
    // "432A462D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D597133743677397A244326462948404D635166546A576E5A7234753778";


    public String generateToken(UserDetails userDetails) {
        return generateToken(new HashMap<>(), userDetails);
    }

    public String generateToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails
    ) {
        return buildToken(extraClaims, userDetails, expiryDuration);
    }

    public String generateRefreshToken(
            UserDetails userDetails
    ) {
        return buildToken(new HashMap<>(), userDetails, refreshExpiryDuration);
    }

    private String buildToken(
            Map<String, Object> extraClaims,
            UserDetails userDetails,
            long expiration
    ) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignScret(), SignatureAlgorithm.HS512)
                .compact();
    }


    private Key getSignScret() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String accountName = extractAccountName(token);
        return (accountName.equals(userDetails.getUsername())) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }


    public String extractAccountName(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public String validateToken(String token) {
        if(token == null){
            return "Invalid verification token";
        }
        if((isTokenExpired(token)))
            return "Token already expired";
        else
            return "valid";
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extracAllClaims(token);
        return claimsResolver.apply(claims);
    }


    private Claims extracAllClaims(String token) {

        return Jwts
                .parserBuilder()
                .setSigningKey(getSignScret())
                .build()
                .parseClaimsJws(token)
                .getBody();

    }
}
