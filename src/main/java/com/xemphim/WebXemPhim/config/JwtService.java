package com.xemphim.WebXemPhim.config;

import com.xemphim.WebXemPhim.dto.AccountDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import static io.jsonwebtoken.security.Keys.secretKeyFor;

@Component
public class JwtService {
    @Value("${expiryDuration}")
    private long expiryDuration;

    @Value("${rf-expiryDuration}")
    private long refreshExpiryDuration;

    @Value("${secret}")
    private String secret;
    // "432A462D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D597133743677397A244326462948404D635166546A576E5A7234753778";



    public String generateJwt(AccountDTO accountDTO) {


        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiryAt = new Date(System.currentTimeMillis() + expiryDuration);
        System.out.print(issuedAt);
        System.out.print(expiryAt);

        Claims claims = Jwts.claims()
                .setIssuer(accountDTO.getAccountName())
                .setIssuedAt(issuedAt)
                .setExpiration(expiryAt);

        // optional claims
        claims.put("role", accountDTO.getUserName());
        claims.put("phoneNumber", accountDTO.getPhoneNumber());
        claims.put("email", accountDTO.getEmail());
        claims.put("userName", accountDTO.getUserName());
        // generate jwt using claims
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(accountDTO.getAccountName())
                .signWith(getSignScret(), SignatureAlgorithm.HS512)
                .compact();
    }
    public String generateRefreshJwt(AccountDTO accountDTO) {


        return null;
    }

    private Key getSignScret() {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        final String accountName = extractAccountName(token);
        return (accountName.equals(userDetails.getUsername())) && isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }


    public String extractAccountName(String token) {
        return extractClaim(token,Claims::getSubject);
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
