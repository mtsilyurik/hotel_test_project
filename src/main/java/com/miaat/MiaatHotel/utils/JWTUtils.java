package com.miaat.MiaatHotel.utils;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.function.Function;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Service
public class JWTUtils {
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // one day
    private final SecretKey secretKey;

    public JWTUtils() {
        String key = "214131R3412412141R33123213123213R123132R3134441212R3123123R3123122R1231421123R12314512";
        byte[] keyBytes = Base64.getDecoder().decode(key.getBytes(StandardCharsets.UTF_8));
        this.secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
    }

    public String generateToken(UserDetails userDetails) {
        return Jwts.builder()
                .subject(userDetails.getUsername())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject)
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsFunction) {
        return claimsFunction.apply(Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token).getBody());
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }
}
